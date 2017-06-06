package syma.agent;

import repast.simphony.space.grid.Grid;
import syma.action.Action;

public class Human extends Agent{
	private int gender; // 0 : male, 1 : female
	private int max_age;
	private int age;
	
	private int hungry;
	private int energy;
	private int mood;
	private Action current_action;
	
	public Human(int i, int j, Grid<Agent> grid2, int gender) {
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
