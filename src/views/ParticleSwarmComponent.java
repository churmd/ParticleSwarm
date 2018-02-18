package views;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Model;

public class ParticleSwarmComponent extends JPanel{
	private static final long serialVersionUID = -7674957251900125641L;

	public ParticleSwarmComponent(Model model, Controller con) {
		super();
		
		ParticlesPanel particles = new ParticlesPanel(model);
		//ButtonPanel buttons = new ButtonPanel(model);
		
		model.addObserver(particles);
		//model.addObserver(buttons);
		
		WeightSliders weights = new WeightSliders(model, con);
		model.addObserver(weights);
		PointOptions pointOptions = new PointOptions(con);
		ParticleSpinner spinner = new ParticleSpinner(model, con);
		model.addObserver(spinner);
		ButtonsPanel buttons = new ButtonsPanel(model, con);
		model.addObserver(buttons);
		
		JPanel options = new JPanel();
		//options.setPreferredSize(new Dimension(200, 600));
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
