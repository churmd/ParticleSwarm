package model;

import javax.swing.text.Position;

import model.vector.Vector;

public class Particle {

	private Vector<Double> position, velocity, updatedPosition, updatedVelocity;

	/**
	 * Creates a particle represented by its position and velocity
	 * 
	 * @param position
	 *            The starting position of the particle
	 * @param velocity
	 *            The starting velocity of the particle
	 */
	public Particle(Vector<Double> position, Vector<Double> velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	/**
	 * Returns the current position of the particle
	 * 
	 * @return the current position of the particle
	 */
	public Vector<Double> getPosition() {
		return position;
	}

	/**
	 * Returns the current velocity of the particle
	 * 
	 * @return the current velocity of the particle
	 */
	public Vector<Double> getVelocity() {
		return velocity;
	}

	/**
	 * Sets the new velocity that this particle will be updated to when update
	 * is called
	 * 
	 * @param vel
	 *            The new velocity for the particle
	 */
	public void updatedVelocity(Vector<Double> vel) {
		updatedVelocity = vel;
	}

	/**
	 * Sets the new position that this particle will be updated to when update
	 * is called
	 * 
	 * @param vel
	 *            The new position of the particle
	 */
	public void updatedPosition(Vector<Double> pos) {
		updatedPosition = pos;
	}

	/**
	 * Updates the particle's position and velocity according to the arguments
	 * passed to updatedPosition and updatedVelocity. If either is null, the
	 * particles position and velocity will not be changed
	 */
	public void update() {
		if (updatedPosition != null && updatedVelocity != null) {
			position = updatedPosition;
			velocity = updatedVelocity;
			updatedPosition = null;
			updatedVelocity = null;
		} else {
			System.err.println("Updated position or velocity not set");
		}
	}
}
