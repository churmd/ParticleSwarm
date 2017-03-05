package views;

import java.awt.BorderLayout;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import model.Model;
import model.environment.Environment;

public class Test {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Swarm");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		
		Environment env = new Environment(100);
		env.genNewParticles();
		Model model = new Model(env);
		ParticlesPanel panel = new ParticlesPanel(model);
		ButtonPanel buttons = new ButtonPanel(model);
		
		model.addObserver(panel);
		
		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.add(buttons, BorderLayout.EAST);
		frame.setVisible(true);
		
//		model.startSwarm();
//		try {
//			TimeUnit.SECONDS.sleep(1);
//			model.addGoal(70, 70);
//			model.addThreat(45, 45);
//			TimeUnit.SECONDS.sleep(4);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		model.stopSwarm();
	}
}
