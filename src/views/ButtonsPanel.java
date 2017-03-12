package views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Model;

public class ButtonsPanel extends JPanel implements Observer{
	private static final long serialVersionUID = -7600206860482790634L;
	
	private Model model;
	private JButton start, stop, reset;

	public ButtonsPanel(Model model) {
		super();
		this.model = model;
		
		setupStart();
		setupStop();
		setupReset();
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createHorizontalGlue());
		add(start);
		add(Box.createHorizontalGlue());
		add(stop);
		add(Box.createHorizontalGlue());
		add(reset);
		add(Box.createHorizontalGlue());
	}
	
	private void setupStart(){
		start = new JButton("Start");
		start.setEnabled(true);
		start.addActionListener(e -> model.startSwarm());
	}
	
	private void setupStop(){
		stop = new JButton("Stop");
		stop.setEnabled(false);
		stop.addActionListener(e -> model.stopSwarm());
	}
	
	private void setupReset(){
		reset = new JButton("Reset");
		reset.setEnabled(true);
		reset.addActionListener(e -> model.resetEnvironment());
	}

	@Override
	public void update(Observable o, Object arg) {
		if (model.isRunning()) {
			stop.setEnabled(true);
			start.setEnabled(false);
			reset.setEnabled(false);
		} else {
			stop.setEnabled(false);
			start.setEnabled(true);
			reset.setEnabled(true);
		}
	}
}
