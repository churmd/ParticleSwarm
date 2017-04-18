package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Model;
import model.environment.Particle;
import model.vector.Vector;

public class ParticlesPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Model model;
	private double radius;
	private Color transGreen, transRed;

	public ParticlesPanel(Model model) {
		super();
		this.model = model;
		
		setPreferredSize(new Dimension(500, 500));
		
		addMouseListener(new ParticleScreenListener(model, this));
		
		radius = 2.5;
		transGreen = new Color(0, 255, 0, 51);
		transRed = new Color(255, 0, 0, 51);
	}

	@Override
	public void paintComponent(Graphics g) {
		//System.out.println("Paint called");
		Graphics2D g2 = (Graphics2D) g;
		int height = getHeight();
		int width = getWidth();
		g2.clearRect(0, 0, width, height);
		g2.setColor(Color.BLACK);
		ArrayList<Particle> particles = model.getParticles();
		
		double neighbourDistanceWidth = (model.getNeighbourDistance() / 100.0) * width;
		double neighbourDistanceHeight = (model.getNeighbourDistance() / 100.0) * height;
		double neighbourDist = Math.min(neighbourDistanceWidth, neighbourDistanceHeight);

		for (Vector<Double> goal : model.getGoals()) {
			double x = goal.getElementAtIndex(0);
			x = (x / 100.0) * width;
			double y = goal.getElementAtIndex(1);
			y = (y / 100.0) * height;
			Ellipse2D.Double areaCircle = centeredCircle(x, y, neighbourDist);
			g2.setColor(transGreen);
			g2.fill(areaCircle);
			Ellipse2D.Double circle = centeredCircle(x, y, radius);
			g2.setColor(Color.GREEN);
			g2.fill(circle);
		}
		
		for (Vector<Double> threat : model.getThreats()) {
			double x = threat.getElementAtIndex(0);
			x = (x / 100.0) * width;
			double y = threat.getElementAtIndex(1);
			y = (y / 100.0) * height;
			Ellipse2D.Double areaCircle = centeredCircle(x, y, neighbourDist);
			g2.setColor(transRed);
			g2.fill(areaCircle);
			Ellipse2D.Double circle = centeredCircle(x, y, radius);
			g2.setColor(Color.RED);
			g2.fill(circle);
		}
		
		for (Particle p : particles) {
			double x = p.getPosition().getElementAtIndex(0);
			x = (x / 100.0) * width;
			double y = p.getPosition().getElementAtIndex(1);
			y = (y / 100.0) * height;
			double xt = p.getPosition().getElementAtIndex(0) + p.getVelocity().getElementAtIndex(0) * 2;
			xt = ((xt / 100.0) * width);
			double yt = p.getPosition().getElementAtIndex(1) + p.getVelocity().getElementAtIndex(1) * 2;
			yt = ((yt / 100.0) * height);
			
			Line2D.Double line = new Line2D.Double(x, y, xt, yt);
			Ellipse2D.Double circle = centeredCircle(x, y, radius);
			g2.setColor(Color.black);
			g2.fill(circle);
			g2.draw(line);
		}

	}
	
	private Ellipse2D.Double centeredCircle(double x, double y, double radius){
		return new Ellipse2D.Double(x - radius, y - radius, 2 * radius, 2 * radius);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//System.out.println("Panel update called");
		repaint();
	}

}
