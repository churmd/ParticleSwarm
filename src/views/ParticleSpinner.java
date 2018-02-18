package views;

import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import controller.Controller;
import model.Model;

public class ParticleSpinner extends JPanel implements Observer{
	private static final long serialVersionUID = -3142967571866465824L;

	private JSpinner spinner;
	private JLabel label;

	private Model model;

	private Controller con;
	
	public ParticleSpinner(Model model, Controller con) {
		super();
		this.model = model;
		this.con = con;
		
		setupSpinner();
		setupLabel();
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		add(label, CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
		add(spinner, CENTER_ALIGNMENT);
		add(Box.createVerticalGlue());
	}
	
	private void setupSpinner(){
		SpinnerNumberModel spinnermodel = new SpinnerNumberModel(model.getNumParticles(), 1, 200, 1);
		spinnermodel.addChangeListener(e -> con.setNumParticles(spinnermodel.getNumber().intValue()));
		spinner = new JSpinner(spinnermodel);
		spinner.setEnabled(true);
	}
	
	private void setupLabel(){
		label = new JLabel();
		label.setText("Number of particles:");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		spinner.setValue(model.getNumParticles());

		if (model.isRunning()) {
			spinner.setEnabled(false);
		} else {
			spinner.setEnabled(true);
		}
	}

}
