package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.environment.Environment;
import model.environment.Particle;
import model.vector.Vector;

public class Model extends Observable {

	private Environment env;
	private boolean running;
	private ScheduledExecutorService schedular;

	public Model(Environment env) {
		super();
		this.env = env;
	}

	public void startSwarm() {
		if (!running) {
			schedular = Executors.newSingleThreadScheduledExecutor();

			Runnable updater = new Runnable() {

				@Override
				public void run() {
					env.updateParticles();
					setChanged();
					notifyObservers();
				}
			};

			running = true;
			schedular.scheduleAtFixedRate(updater, 0, 50, TimeUnit.MILLISECONDS);
		}
	}

	public void stopSwarm() {
		if (running) {
			schedular.shutdown();
			running = false;
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
}
