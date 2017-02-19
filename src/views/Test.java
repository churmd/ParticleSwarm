package views;

import java.util.concurrent.TimeUnit;

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
		
		con.startSwarm();
		try {
			TimeUnit.SECONDS.sleep(1);
			con.addGoal(70, 70);
			con.addThreat(45, 45);
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		con.stopSwarm();
	}
}
