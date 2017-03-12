package views;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import model.Model;

public class PointOptions extends JPanel{
	private static final long serialVersionUID = -3892650511229855524L;
	
	private Model model;
	private JRadioButton goal, threat;
	private ButtonGroup group;
	private JLabel label;

	public PointOptions(Model model) {
		super();
		this.model = model;
		
		setupLabel();
		setupRadioButtons();
		
		JPanel radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new BoxLayout(radioButtonPanel, BoxLayout.X_AXIS));
		radioButtonPanel.add(Box.createHorizontalGlue());
		radioButtonPanel.add(goal);
		radioButtonPanel.add(Box.createHorizontalGlue());
		radioButtonPanel.add(threat);
		radioButtonPanel.add(Box.createHorizontalGlue());
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		add(label);
		add(Box.createVerticalGlue());
		add(radioButtonPanel);
		add(Box.createVerticalGlue());
	}
	
	private void setupRadioButtons(){
		goal = new JRadioButton("Goal");
		goal.addActionListener(e -> model.addingGoalMode());
		goal.setSelected(false);
		
		threat = new JRadioButton("Threat");
		threat.addActionListener(e -> model.addingThreatMode());
		threat.setSelected(true);
		model.addingThreatMode();
		
		group = new ButtonGroup();
		group.add(goal);
		group.add(threat);
	}
	
	private void setupLabel(){
		label = new JLabel();
		label.setText("<html>" + "Add pointof type:" + "</html>");
	}
}
