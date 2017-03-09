package views;

import javax.swing.JFrame;

import model.Model;
import model.environment.Environment;

public class ParticleSwarmGUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Particle Swarm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Environment env = new Environment(100);
		Model model = new Model(env);
		ParticleSwarmComponent comp = new ParticleSwarmComponent(model);
		
		frame.add(comp);
		frame.pack();
		frame.setVisible(true);
	}
}
