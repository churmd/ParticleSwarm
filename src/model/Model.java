package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.environment.Environment;
import model.environment.Particle;
import model.environment.Weightings;
import model.vector.Vector;

public class Model extends Observable {

	private Environment env;
	private boolean running;
	private ScheduledExecutorService schedular;
	private boolean addingThreat;
	private Weightings weights;

	public Model(Environment env) {
		super();
		this.env = env;
		weights = env.getWeightings();
	}

	public void startSwarm() {
		if (!running) {
			schedular = Executors.newSingleThreadScheduledExecutor();

			Runnable updater = new Runnable() {

				@Override
				public void run() {
					//System.out.println(System.currentTimeMillis());
					env.updateParticles();
					setChanged();
					notifyObservers();
				}
			};

			running = true;
			schedular.scheduleAtFixedRate(updater, 0, 50, TimeUnit.MILLISECONDS);
			setChanged();
			notifyObservers();
		}
	}

	public void stopSwarm() {
		if (running) {
			schedular.shutdown();
			running = false;
			setChanged();
			notifyObservers();
		}
	}
	
	public void resetEnvironment(){
		if(!running){
			env.reset();
			setChanged();
			notifyObservers();
		}
	}

	public void addingThreatMode(){
		addingThreat = true;
	}
	
	public void addingGoalMode(){
		addingThreat = false;
	}
	
	public void createThreatOrGoal(double x, double y){
		//System.out.println("create called:" + x + " " + y);
		if(addingThreat){
			addThreat(x, y);
		} else {
			addGoal(x, y);
		}
	}
	
	public void addGoal(double x, double y) {
		env.addGoal(x, y);
		setChanged();
		notifyObservers();
	}

	public void addThreat(double x, double y) {
		env.addThreat(x, y);
		setChanged();
		notifyObservers();
	}

	public ArrayList<Particle> getParticles() {
		return env.getParticles();
	}

	public ArrayList<Vector<Double>> getGoals() {
		return env.getGoals();
	}

	public ArrayList<Vector<Double>> getThreats() {
		return env.getThreats();
	}
	
	public void setNumParticles(int num){
		if(!running){
			env.setNumParticles(num);
			setChanged();
			notifyObservers();
		}
	}
	
	public int getNumParticles(){
		return env.getNumParticles();
	}
	
	public double getNeighbourDistance(){
		return env.getNeighbourDistance();
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public double getWidth(){
		return 100.0;
	}
	
	public double getHeight(){
		return 100.0;
	}
	
	public void setAlignmentWeighting(double w){
		weights.setAlignment(w);
	}
	
	public double getAlignmentWeighting(){
		return weights.getAlignment();
	}
	
	public void setSeparationWeighting(double w){
		weights.setSeparation(w);
	}
	
	public double getSeparationWeighting(){
		return weights.getSeparation();
	}
	
	public void setCohesionWeighting(double w){
		weights.setCohesion(w);
	}
	
	public double getCohesionWeighting(){
		return weights.getCohesion();
	}
	
	public void setGoalWeighting(double w){
		weights.setGoal(w);
	}
	
	public double getGoalWeighting(){
		return weights.getGoal();
	}
	
	public void setThreatWeighting(double w){
		weights.setThreat(w);
	}
	
	public double getThreatWeighting(){
		return weights.getThreat();
	}
}
