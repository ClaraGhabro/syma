package agent;

import action.Action;
import repast.simphony.space.grid.Grid;

public class HumanAgent extends Agent{
	private int gender; // 0 : male, 1 : female
	private int max_age;
	private int age;
	
	private int hungry;
	private int energy;
	private int mood;
	private Action current_action;
	
	public HumanAgent(int i, int j, Grid<Agent> grid2, int gender) {
		super(i, j, grid2);
		this.gender = gender;
	}


	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
	
	public void setEnergy(int newEnergy) {
		this.energy += newEnergy;
	}

	public void setHungry(int newHungry) {
		this.hungry += newHungry;
	}
	
	public void setMood(int newMood) {
		this.mood += newMood;
	}
}
