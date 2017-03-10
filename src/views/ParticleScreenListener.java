package views;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import model.Model;

public class ParticleScreenListener implements MouseInputListener {

	private Model model;
	private JPanel particlePanel;
	
	public ParticleScreenListener(Model model, JPanel particlePanel) {
		this.model = model;
		this.particlePanel = particlePanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Double x = new Double(e.getX());
		x = (x / (double) particlePanel.getWidth()) * model.getWidth();

		Double y = new Double(e.getY());
		y = (y / (double) particlePanel.getHeight()) * model.getHeight();
		
		model.createThreatOrGoal(x, y);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
