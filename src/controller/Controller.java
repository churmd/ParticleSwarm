package controller;

import model.Model;

public class Controller {

	private Model model;
	
	public Controller(Model model){
		this.model = model;
	}
	
	public void startSwarm(){
		model.startSwarm();
	}

	
	public void stopSwarm(){
		model.stopSwarm();
	}
	
	public void resetEnvironment(){
		model.resetEnvironment();
	}
	
	public void addingThreatMode(){
		model.addingThreatMode();
	}
	
	public void addingGoalMode(){
		model.addingGoalMode();
	}
	
	public void createThreatOrGoal(double x, double y){
		model.createThreatOrGoal(x, y);
	}
	
	public void addGoal(double x, double y) {
		model.addGoal(x, y);
	}

	public void addThreat(double x, double y) {
		model.addThreat(x, y);
	}
	
	public void setNumParticles(int num){
		model.setNumParticles(num);
	}
	
	public void setAlignmentWeighting(double w){
		model.setAlignmentWeighting(w);
	}
	
	public void setSeparationWeighting(double w){
		model.setSeparationWeighting(w);
	}
	
	public void setCohesionWeighting(double w){
		model.setCohesionWeighting(w);
	}
	
	public void setGoalWeighting(double w){
		model.setGoalWeighting(w);
	}
	
	public void setThreatWeighting(double w){
		model.setThreatWeighting(w);
	}
}
