package views;

import javax.swing.JFrame;

import model.Enviroment;
import model.Model;

public class ParticleSwarmGUI {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Particle Swarm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		Enviroment env = new Enviroment(100);
		Model model = new Model(env);
		ParticleSwarmComponent comp = new ParticleSwarmComponent(model);
		
		frame.add(comp);
		frame.setVisible(true);
	}
}
