package controller;

import javax.swing.plaf.SliderUI;

import model.Enviroment;

public class Controller {

	private Enviroment enviroment;
	private boolean run;
	private Thread t;
	
	public Controller(Enviroment enviroment){
		this.enviroment = enviroment;
	}
	
	public void startSwarm(){
		run = true;
		Runnable updater = new Runnable() {
			
			@Override
			public void run() {
				while(run){
					enviroment.updateParticles();
				}
				
			}
		};
		t = new Thread(updater);
		t.start();
	}
	
	public void startSwarmTest(){
		Runnable updater = new Runnable() {
			int i = 0;
			@Override
			public void run() {
				while(i<200){
					enviroment.updateParticles();
					i++;
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		};
		t = new Thread(updater);
		t.start();
	}
	
	public void stopSwarm(){
		run = false;
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
