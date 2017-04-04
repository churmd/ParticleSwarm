package views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;

import model.Model;

public class WeightSliders extends JPanel implements Observer{
	private static final long serialVersionUID = -4897738360477893186L;
	
	private Model model;
	private JSlider cohesion, alignment, separation, goal, threat;
	private JLabel labelTitle, labelCohesion, labelAlignment, labelSeparation, labelGoal, labelThreat;
	
	public WeightSliders(Model model) {
		super();
		this.model = model;
		
		setupSliders();
		setupLabels();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		add(labelTitle);
		add(Box.createVerticalGlue());
		add(labelCohesion);
		add(Box.createVerticalGlue());
		add(cohesion);
		add(Box.createVerticalGlue());
		add(labelAlignment);
		add(Box.createVerticalGlue());
		add(alignment);
		add(Box.createVerticalGlue());
		add(labelSeparation);
		add(Box.createVerticalGlue());
		add(separation);
		add(Box.createVerticalGlue());
		add(labelGoal);
		add(Box.createVerticalGlue());
		add(goal);
		add(Box.createVerticalGlue());
		add(labelThreat);
		add(Box.createVerticalGlue());
		add(threat);
		add(Box.createVerticalGlue());
	}
	
	private void setupSliders() {
		Double i;
		int init;
		
		i = new Double(model.getCohesionWeighting());
		i = i * 10.0;
		init = i.intValue();
		cohesion = new JSlider(0, 100, init);;
		cohesion.addChangeListener(e -> model.setCohesionWeighting(cohesion.getValue() * 1.0 / 10.0));
		
		i = new Double(model.getAlignmentWeighting());
		i = i * 10.0;
		init = i.intValue();
		alignment = new JSlider(0, 100, init);;
		alignment.addChangeListener(e -> model.setAlignmentWeighting(alignment.getValue() * 1.0 / 10.0));
		
		i = new Double(model.getSeparationWeighting());
		i = i * 10.0;
		init = i.intValue();
		separation = new JSlider(0, 100, init);;
		separation.addChangeListener(e -> model.setSeparationWeighting(separation.getValue() * 1.0 / 10.0));
		
		i = new Double(model.getGoalWeighting());
		i = i * 10.0;
		init = i.intValue();
		goal = new JSlider(0, 100, init);;
		goal.addChangeListener(e -> model.setGoalWeighting(goal.getValue() * 1.0 / 10.0));
		
		i = new Double(model.getThreatWeighting());
		i = i * 10.0;
		init = i.intValue();
		threat = new JSlider(0, 100, init);;
		threat.addChangeListener(e -> model.setThreatWeighting(threat.getValue() * 1.0 / 10.0));
		
	}
	
	private void setupLabels(){
		labelTitle = new JLabel("Weightings");
		labelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font labelfont = labelTitle.getFont();
		labelTitle.setFont(new Font(labelfont.getFontName(), Font.PLAIN, labelfont.getSize() + 2));
		
		labelCohesion = new JLabel("Cohesion");
		labelCohesion.setAlignmentX(Component.CENTER_ALIGNMENT);

		labelAlignment = new JLabel("Alignment");
		labelAlignment.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		labelSeparation = new JLabel("Separation");
		labelSeparation.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		labelGoal = new JLabel("Goal seeking");
		labelGoal.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		labelThreat = new JLabel("Threat avoidance");
		labelThreat.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		Double v;
		int value;
		
		v = new Double(model.getCohesionWeighting());
		v = v * 10.0;
		value = v.intValue();
		cohesion.setValue(value);
		
		v = new Double(model.getAlignmentWeighting());
		v = v * 10.0;
		value = v.intValue();
		alignment.setValue(value);
		
		v = new Double(model.getSeparationWeighting());
		v = v * 10.0;
		value = v.intValue();
		separation.setValue(value);
		
		v = new Double(model.getGoalWeighting());
		v = v * 10.0;
		value = v.intValue();
		goal.setValue(value);
		
		v = new Double(model.getThreatWeighting());
		v = v * 10.0;
		value = v.intValue();
		threat.setValue(value);
		
	}

}
