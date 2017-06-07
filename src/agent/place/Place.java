package agent.place;

import agent.Agent;
import agent.Human;
import repast.simphony.space.grid.Grid;

public class Place extends Agent {
	private PlaceType type;
	private int mood;
	private int energy;
	private int hunger;

	public Place(int i, int j, Grid<Agent> grid, PlaceType type, int mood, int energy, int hunger) {
		super(i, j, grid);
		this.type = type;
		this.mood = mood;
		this.energy = energy;
		this.hunger = hunger;
	}

	public void affect(Human human) {
		human.addMood(this.mood);
		human.addEnergy(this.energy);
		human.addHunger(this.hunger);
	}

	public int getMood() {
		return mood;
	}
	public int getEnergy() {
		return energy;
	}
	public int getHunger() {
		return hunger;
	}
	public PlaceType getType() {
		return this.type;
	}

	public boolean isAccessibleTo(Human human) {
		return this.type != PlaceType.WATER;
	}

	@Override
	public void update() {}

	@Override
	public void step() {}
}