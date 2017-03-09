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

	public ParticlesPanel(Model model) {
		super();
		this.model = model;
		
		setPreferredSize(new Dimension(500, 500));
		
		radius = 2.5;
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
			g2.fill(circle);
			g2.draw(line);
		}

		for (Vector<Double> goal : model.getGoals()) {
			g2.setColor(Color.GREEN);
			double x = goal.getElementAtIndex(0);
			x = (x / 100.0) * width;
			double y = goal.getElementAtIndex(1);
			y = (y / 100.0) * height;
			Ellipse2D.Double circle = centeredCircle(x, y, radius);
			g2.fill(circle);
		}
		
		for (Vector<Double> threat : model.getThreats()) {
			g2.setColor(Color.RED);
			double x = threat.getElementAtIndex(0);
			x = (x / 100.0) * width;
			double y = threat.getElementAtIndex(1);
			y = (y / 100.0) * height;
			Ellipse2D.Double circle = centeredCircle(x, y, radius);
			g2.fill(circle);
		}
		
		//System.out.println("Repainted");
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
