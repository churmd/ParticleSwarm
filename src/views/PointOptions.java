package views;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import controller.Controller;
import model.Model;

public class PointOptions extends JPanel{
	private static final long serialVersionUID = -3892650511229855524L;
	
	private JRadioButton goal, threat;
	private ButtonGroup group;
	private JLabel label;

	private Controller con;

	public PointOptions(Controller con) {
		super();
		this.con = con;
		
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
		add(label, CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		add(radioButtonPanel, CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
	}
	
	private void setupRadioButtons(){
		goal = new JRadioButton("Goal");
		goal.addActionListener(e -> con.addingGoalMode());
		goal.setSelected(false);
		
		threat = new JRadioButton("Threat");
		threat.addActionListener(e -> con.addingThreatMode());
		threat.setSelected(true);
		con.addingThreatMode();
		
		group = new ButtonGroup();
		group.add(goal);
		group.add(threat);
	}
	
	private void setupLabel(){
		label = new JLabel();
		label.setText("Add a point of type:");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
}
