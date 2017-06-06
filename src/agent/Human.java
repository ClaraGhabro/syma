package agent;

import action.Action;
import agent.place.House;
import repast.simphony.space.grid.Grid;
import work.Work;

public class Human extends Agent{
	private int gender; // 0 : male, 1 : female
	private int max_age;
	private int age;

	private int mood;
	private int energy;
	private int hunger;
	
	private Action current_action;
	private Work work;
	private House house;
	
	public Human(int i, int j, Grid<Agent> grid2, int gender) {
		super(i, j, grid2);
		this.gender = gender;
	}

	public void addMood(int mood) {
		this.mood += mood;
	}
	public void addEnergy(int energy) {
		this.energy += energy;
	}
	public void addHunger(int hunger) {
		this.hunger += hunger;
	}
	public Work getWork() {
		return this.work;
	}
	public House getHouse() {
		return this.house;
	}
	
	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
}