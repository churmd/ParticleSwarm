package views;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import model.Model;

public class ButtonPanel extends JPanel implements Observer {
	private static final long serialVersionUID = -2844646719687839042L;

	private JButton start, stop;
	private JLabel numParticlesLabel;
	private JSpinner numParticles;

	private Model model;

	public ButtonPanel(Model model) {
		super();
		this.model = model;

		startButton();
		stopButton();
		numParticlesLabel();
		particleNumSpinner();

		setLayout(new GridLayout(2, 2));
		add(numParticlesLabel);
		add(numParticles);
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
