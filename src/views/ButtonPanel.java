package views;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;

public class ButtonPanel extends JPanel {
	private static final long serialVersionUID = -2844646719687839042L;

	private JButton start, stop;

	private Model model;

	public ButtonPanel(Model model) {
		super();
		this.model = model;

		startButton();
		stopButton();

		setLayout(new GridLayout(2, 1));
		add(start);
		add(stop);
	}

	private void startButton() {
		start = new JButton("Start");
		start.addActionListener(e -> {
			model.startSwarm();
			start.setEnabled(false);
			stop.setEnabled(true);
		});
		start.setEnabled(true);
	}

	private void stopButton() {
		stop = new JButton("stop");
		stop.addActionListener(e -> {
			model.stopSwarm();
			stop.setEnabled(false);
			start.setEnabled(true);
		});
		stop.setEnabled(false);
	}
}
