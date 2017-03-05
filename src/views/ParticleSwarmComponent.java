package views;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import model.Model;

public class ParticleSwarmComponent extends JPanel{
	private static final long serialVersionUID = -7674957251900125641L;

	public ParticleSwarmComponent(Model model) {
		super();
		
		ParticlesPanel particles = new ParticlesPanel(model);
		ButtonPanel buttons = new ButtonPanel(model);
		
		model.addObserver(particles);
		model.addObserver(buttons);
		
		setLayout(new BorderLayout());
		add(particles, BorderLayout.CENTER);
		add(buttons, BorderLayout.EAST);
	}
}
