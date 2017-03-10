package views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import model.Model;

public class ButtonPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -2844646719687839042L;

	private JButton start, stop;
	private JLabel numParticlesLabel, threatGoalLabel;
	private JSpinner numParticles;
	private ButtonGroup threatGoalToggle;
	private JRadioButton goalRadio, threatRadio;

	private Model model;

	public ButtonPanel(Model model) {
		super();
		this.model = model;
		
		setPreferredSize(new Dimension(200, 500));

		startButton();
		stopButton();
		threatGoalLabel();
		threatOrGoalRadioButtons();
		numParticlesLabel();
		particleNumSpinner();

		setLayout(new GridLayout(4, 2));
		add(numParticlesLabel);
		add(numParticles);
		add(goalRadio);
		add(threatRadio);
		add(start);
		add(stop);
	}

	private void startButton() {
		start = new JButton("Start");
		start.addActionListener(e -> model.startSwarm());
		start.setEnabled(true);
	}

	private void stopButton() {
		stop = new JButton("stop");
		stop.addActionListener(e -> model.stopSwarm());
		stop.setEnabled(false);
	}
	
	private void particleNumSpinner(){
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(model.getNumParticles(), 1, 200, 1);
		spinnerModel.addChangeListener(e -> model.setNumParticles(spinnerModel.getNumber().intValue()));
		numParticles = new JSpinner(spinnerModel);
	}

	private void numParticlesLabel() {
		numParticlesLabel = new JLabel();
		numParticlesLabel.setText("Number of Particles:");
	}

	private void threatOrGoalRadioButtons(){
		goalRadio = new JRadioButton("Goal");
		goalRadio.addActionListener(e -> model.addingGoalMode());
		goalRadio.setSelected(false);
		
		threatRadio = new JRadioButton("Threat");
		threatRadio.addActionListener(e -> model.addingThreatMode());
		threatRadio.setSelected(true);
		model.addingThreatMode();
		
		threatGoalToggle = new ButtonGroup();
		threatGoalToggle.add(goalRadio);
		threatGoalToggle.add(threatRadio);
	}
	
	private void threatGoalLabel(){
		threatGoalLabel = new JLabel();
		threatGoalLabel.setText("Type of point to add:");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		numParticles.setValue(model.getNumParticles());

		if (model.isRunning()) {
			stop.setEnabled(true);
			start.setEnabled(false);
			numParticles.setEnabled(false);
		} else {
			stop.setEnabled(false);
			start.setEnabled(true);
			numParticles.setEnabled(true);
		}
	}
}
