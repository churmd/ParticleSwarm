package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

import model.vector.Vector;
import model.vector.VectorCalcDouble;
import model.vector.VectorDimensionException;

public class Enviroment extends Observable {

	private ArrayList<Particle> particles;
	private VectorCalcDouble calc;
	private int dimension;
	private Double width, height;
	private int numParticles;
	private double neighbourDistance;
	private ArrayList<Vector<Double>> goals;
	private ArrayList<Vector<Double>> threats;
	private ReentrantReadWriteLock rwl;
	private ReadLock rl;
	private WriteLock wl;

	public Enviroment(int numParticles) {
		rwl = new ReentrantReadWriteLock(true);
		rl = rwl.readLock();
		wl = rwl.writeLock();
		
		calc = new VectorCalcDouble();
		this.numParticles = numParticles;
		dimension = 2;
		neighbourDistance = 20.0;
		width = 100.0;
		height = 100.0;
		
		genNewParticles();
		
		goals = new ArrayList<>();
		addGoal(50, 50);
		addGoal(25,  25);
		
		threats = new ArrayList<>();
		addThreat(37.5, 37.5);
	}

	public ArrayList<Vector<Double>> getGoals() {
		rl.lock();
		try {
			return goals;
		} finally {
			rl.unlock();
		}
	}
	
	public void addGoal(double x, double y){
		wl.lock();
		try {
			goals.add(new Vector<>(new Double[] {x,y}));
		} finally {
			wl.unlock();
		}
	}
	
	public ArrayList<Vector<Double>> getThreats() {
		rl.lock();
		try {
			return threats;
		} finally {
			rl.unlock();
		}
	}
	
	public void addThreat(double x, double y){
		wl.lock();
		try {
			threats.add(new Vector<>(new Double[] {x,y}));
		} finally {
			wl.unlock();
		}
	}

	public void setNumParticles(int numParticles) {
		this.numParticles = numParticles;
	}

	public ArrayList<Particle> getParticles() {
		return particles;
	}

	public void genNewParticles() {
		particles = new ArrayList<>(numParticles);
		Random gen = new Random();
		for (int i = 0; i < numParticles; i++) {
			Vector<Double> position = new Vector<>(
					new Double[] { (double) gen.nextInt(100), (double) gen.nextInt(100) });
			Double v1 = gen.nextDouble();
			if (gen.nextBoolean()) {
				v1 = -v1;
			}
			double v2 = gen.nextDouble();
			if (gen.nextBoolean()) {
				v2 = -v2;
			}
			Vector<Double> velocity = new Vector<>(new Double[] { v1, v2 });
			Particle p = new Particle(position, velocity);
			particles.add(p);
		}
	}

	private Vector<Double> cohesion(Particle p) throws VectorDimensionException {
		int count = 0;
		Vector<Double> sumOfPos = new Vector<>(new Double[] { 0.0, 0.0 });
		for (int i = 0; i < particles.size(); i++) {
			Particle other = particles.get(i);
			double dist = calc.distanceBetweenVectors(p.getPosition(), other.getPosition());
			if (dist > 0 && dist < neighbourDistance) {
				sumOfPos = calc.add(sumOfPos, other.getPosition());
				count++;
			}
		}

		if (count > 0) {
			Vector<Double> averagePos = calc.divideConstant(sumOfPos, count);
			Vector<Double> velocity = calc.subtract(averagePos, p.getPosition());
			return calc.normalise(velocity);
		} else {
			return new Vector<>(new Double[] { 0.0, 0.0 });
		}
	}

	private Vector<Double> alignment(Particle p) throws VectorDimensionException {
		int count = 0;
		Vector<Double> sumOfVel = new Vector<>(new Double[] { 0.0, 0.0 });
		for (int i = 0; i < particles.size(); i++) {
			Particle other = particles.get(i);
			double dist = calc.distanceBetweenVectors(p.getPosition(), other.getPosition());
			if (dist > 0 && dist < neighbourDistance) {
				sumOfVel = calc.add(sumOfVel, other.getVelocity());
				count++;
			}
		}
		if (count > 0) {
			Vector<Double> averageVel = calc.divideConstant(sumOfVel, count);
			return calc.normalise(averageVel);
		} else {
			return new Vector<>(new Double[] { 0.0, 0.0 });
		}
	}

	private Vector<Double> separation(Particle p) throws VectorDimensionException {
		double desiredSeparation = neighbourDistance / 4.0;
		int count = 0;
		Vector<Double> sumOfDist = new Vector<>(new Double[] { 0.0, 0.0 });
		for (int i = 0; i < particles.size(); i++) {
			Particle other = particles.get(i);
			double dist = calc.distanceBetweenVectors(p.getPosition(), other.getPosition());
			// if(dist < neighbourDistance){
			// Vector<Double> diff = calc.subtract(p.getPosition(),
			// other.getPosition());
			// sumOfDist = calc.subtract(sumOfDist, diff);
			// }
			if (dist > 0 && dist < desiredSeparation) {
				Vector<Double> diff = calc.subtract(other.getPosition(), p.getPosition());
				diff = calc.normalise(diff);
				diff = calc.multiplyConstant(diff, desiredSeparation - dist);
				sumOfDist = calc.add(sumOfDist, diff);
				count++;
			}
		}
		// Vector<Double> negated = calc.multiplyConstant(sumOfDist, -1.0);
		// return calc.normalise(negated);

		if (count > 0) {
			Vector<Double> averageDist = calc.divideConstant(sumOfDist, count);
			// negate vector so particle steers away from others
			averageDist = calc.multiplyConstant(averageDist, -1.0);
			return calc.normalise(averageDist);
		} else {
			return new Vector<>(new Double[] { 0.0, 0.0 });
		}
	}

	private Vector<Double> seekGoal(Particle p) throws VectorDimensionException {
		Vector<Double> total = new Vector<>(new Double[] {0.0,0.0});
		
		for (Vector<Double> goal : getGoals()) {
			double dist = calc.distanceBetweenVectors(p.getPosition(), goal);
			if (dist < neighbourDistance) {
				Vector<Double> velocityToGoal = calc.subtract(goal, p.getPosition());
				velocityToGoal = calc.multiplyConstant(velocityToGoal, neighbourDistance-dist);
				total = calc.add(total, velocityToGoal);
			}
		}
		
		return calc.normalise(total);
	}
	
	private Vector<Double> avoidThreats(Particle p) throws VectorDimensionException{
		Vector<Double> total = new Vector<>(new Double[] {0.0,0.0});
		
		for(Vector<Double> threat : getThreats()){
			double dist = calc.distanceBetweenVectors(p.getPosition(), threat);
			if(dist < neighbourDistance){
				Vector<Double> velocityFromThreat = calc.subtract(p.getPosition(), threat);
				velocityFromThreat = calc.multiplyConstant(velocityFromThreat, neighbourDistance-dist);
				total = calc.add(total, velocityFromThreat);
			}
		}
		
		return calc.normalise(total);
	}

	/**
	 * Calculates if a particle will end up out of bounds by looking at its
	 * position and velocity, and returns a new velocity that will prevent it
	 * from going out of bounds
	 * 
	 * @param pos
	 *            The current position of the particle
	 * @param vel
	 *            The velocity of the particle
	 * @return A velocity that will keep the particle inbounds
	 * @throws VectorDimensionException
	 */
	private Vector<Double> keepInBounds(Vector<Double> pos, Vector<Double> vel) throws VectorDimensionException {
		Vector<Double> updatedPos = calc.add(pos, vel);

		double x = updatedPos.getElementAtIndex(0);
		double y = updatedPos.getElementAtIndex(1);
		boolean needChanging = false;

		if (x < 0) {
			x = 0.0;
			needChanging = true;
		} else if (x > width) {
			x = width;
			needChanging = true;
		}

		if (y < 0) {
			y = 0.0;
			needChanging = true;
		} else if (y > height) {
			y = height;
			needChanging = true;
		}

		if (needChanging) {
			Vector<Double> desiredPos = new Vector<>(new Double[] { x, y });
			Vector<Double> newVelocity = calc.subtract(desiredPos, pos);
			return calc.normalise(newVelocity);
		} else {
			return vel;
		}

	}

	private Vector<Double> smoothVelocity(Vector<Double> oldVel, Vector<Double> newVel) throws VectorDimensionException{
		double a = 0.6;
		Vector<Double> scaleOldVel = calc.multiplyConstant(oldVel, 1-a);
		Vector<Double> scaleNewVel = calc.multiplyConstant(newVel, a);
		return calc.add(scaleOldVel, scaleNewVel);
	}
	
	/**
	 * This a class that extends callable so that particles can be updated 
	 * in parallel and execptions can be handled
	 * @author daniel
	 *
	 */
	private class UpdateAParticle implements Callable<Particle> {
		
		private Particle p;

		public UpdateAParticle(Particle p) {
			this.p = p;
		}

		@Override
		public Particle call() throws Exception {
			ArrayList<Vector<Double>> vectors = new ArrayList<>(6);
			vectors.add(p.getVelocity());
			vectors.add(calc.multiplyConstant(cohesion(p), 0.8));
			vectors.add(alignment(p));
			vectors.add(calc.multiplyConstant(separation(p), 2));
			vectors.add(calc.multiplyConstant(seekGoal(p), 1));
			vectors.add(avoidThreats(p));

			Vector<Double> newVelocity = calc.add(vectors);
			newVelocity = smoothVelocity(p.getVelocity(), newVelocity);
			newVelocity = calc.normalise(newVelocity);
			newVelocity = keepInBounds(p.getPosition(), newVelocity);

			Vector<Double> newPosition = calc.add(p.getPosition(), newVelocity);

			p.updatedPosition(newPosition);
			p.updatedVelocity(newVelocity);
			
			return p;
		}
		
	}
	
	public void updateParticles() {
		try {
			ExecutorService pool = Executors.newFixedThreadPool(10);
			
			ArrayList<Future<Particle>> futures = new ArrayList<>(numParticles);
			
			for (Particle p : particles) {
				Future<Particle> f = pool.submit(new UpdateAParticle(p));
				futures.add(f);
			}

			for (Future<Particle> f : futures) {
				Particle p = f.get();
				p.update();
			}
			
			pool.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
			// TODO tidy up shutdown
			System.exit(1);
		}
		System.out.println("Particles updated");
		setChanged();
		notifyObservers();
	}
}
