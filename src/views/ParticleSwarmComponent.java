package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import model.Model;

public class ParticleSwarmComponent extends JPanel{
	private static final long serialVersionUID = -7674957251900125641L;

	public ParticleSwarmComponent(Model model) {
		super();
		
		ParticlesPanel particles = new ParticlesPanel(model);
		//ButtonPanel buttons = new ButtonPanel(model);
		
		model.addObserver(particles);
		//model.addObserver(buttons);
		
		WeightSliders weights = new WeightSliders(model);
		model.addObserver(weights);
		PointOptions pointOptions = new PointOptions(model);
		ParticleSpinner spinner = new ParticleSpinner(model);
		model.addObserver(spinner);
		ButtonsPanel buttons = new ButtonsPanel(model);
		model.addObserver(buttons);
		
		JPanel options = new JPanel();
		options.setPreferredSize(new Dimension(200, 600));
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));
		options.add(Box.createVerticalGlue());
		options.add(weights);
		options.add(Box.createVerticalGlue());
		options.add(pointOptions);
		options.add(Box.createVerticalGlue());
		options.add(spinner);
		options.add(Box.createVerticalGlue());
		options.add(buttons);
		options.add(Box.createVerticalGlue());
		
		setLayout(new BorderLayout());
		add(particles, BorderLayout.CENTER);
		add(options, BorderLayout.EAST);
	}
}
