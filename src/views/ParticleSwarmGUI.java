package views;

import javax.swing.JFrame;

import controller.Controller;
import model.Model;
import model.environment.Environment;

public class ParticleSwarmGUI extends JFrame {

	public ParticleSwarmGUI() {
		super();

		this.setTitle("Particle Swarm");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Environment env = new Environment(100);
		Model model = new Model(env);
		Controller con = new Controller(model);
		ParticleSwarmComponent comp = new ParticleSwarmComponent(model, con);

		this.add(comp);
		this.pack();
		this.setVisible(true);
	}
}
