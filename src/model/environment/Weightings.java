package model.environment;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class Weightings {

	private ReentrantReadWriteLock rwl;
	private ReadLock rl;
	private WriteLock wl;
	private double cohesion, alignment, separation, goal, threat;

	public Weightings(double cohesion, double alignment, double separation, double goal, double threat) {
		rwl = new ReentrantReadWriteLock(true);
		rl = rwl.readLock();
		wl = rwl.writeLock();
		this.cohesion = cohesion;
		this.alignment = alignment;
		this.separation = separation;
		this.goal = goal;
		this.threat = threat;
	}

	public void setCohesion(double n) {
		wl.lock();
		try {
			cohesion = n;
		} finally {
			wl.unlock();
		}
	}

	public double getCohesion() {
		rl.lock();
		try {
			return cohesion;
		} finally {
			rl.unlock();
		}
	}
	
	public void setAlignment(double n) {
		wl.lock();
		try {
			alignment = n;
		} finally {
			wl.unlock();
		}
	}

	public double getAlignment() {
		rl.lock();
		try {
			return alignment;
		} finally {
			rl.unlock();
		}
	}
	
	public void setSeparation(double n) {
		wl.lock();
		try {
			separation = n;
		} finally {
			wl.unlock();
		}
	}

	public double getSeparation() {
		rl.lock();
		try {
			return separation;
		} finally {
			rl.unlock();
		}
	}
	
	public void setGoal(double n) {
		wl.lock();
		try {
			goal = n;
		} finally {
			wl.unlock();
		}
	}

	public double getGoal() {
		rl.lock();
		try {
			return goal;
		} finally {
			rl.unlock();
		}
	}
	
	public void setThreat(double n) {
		wl.lock();
		try {
			threat = n;
		} finally {
			wl.unlock();
		}
	}

	public double getThreat() {
		rl.lock();
		try {
			return threat;
		} finally {
			rl.unlock();
		}
	}
}
