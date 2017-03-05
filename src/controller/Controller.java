package controller;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.environment.Environment;

public class Controller {

	private Environment enviroment;
	private boolean running;
	private Thread t;
	private ScheduledExecutorService schedular;
	
	public Controller(Environment enviroment){
		this.enviroment = enviroment;
	}
	
	public void startSwarm(){
		schedular = Executors.newSingleThreadScheduledExecutor();
		
		Runnable updater = new Runnable() {
			
			@Override
			public void run() {
				enviroment.updateParticles();
			}
		};
		
		running = true;
		schedular.scheduleAtFixedRate(updater, 0, 50, TimeUnit.MILLISECONDS);
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
		if(running){
			schedular.shutdown();
			running = false;
		} 
	}
	
	public void addGoal(double x, double y){
		enviroment.addGoal(x, y);
	}
	
	public void addThreat(double x, double y){
		enviroment.addThreat(x, y);
	}
}
