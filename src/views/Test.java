package views;

import javax.swing.JFrame;

import controller.Controller;
import model.Enviroment;

public class Test {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Swarm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		Enviroment env = new Enviroment(100);
		env.genNewParticles();
		Controller con = new Controller(env);
		ParticlesPanel panel = new ParticlesPanel(env);
		env.addObserver(panel);
		
		frame.add(panel);
		frame.setVisible(true);
		
		con.startSwarmTest();
	}
}
