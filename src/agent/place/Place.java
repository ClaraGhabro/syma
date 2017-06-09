package agent.place;

import agent.Agent;
import agent.Human;
import repast.simphony.space.grid.Grid;

public class Place extends Agent {
	private PlaceType type;
	public int mood;
	public int energy;
	public int hunger;

	public Place(int i, int j, Grid<Agent> grid, PlaceType type, int mood, int energy, int hunger) {
		super(i, j, grid);
		this.type = type;
		this.mood = mood; // DEBUG + 1
		this.energy = energy; // DEBUG + 1
		this.hunger = hunger; // DEBUG + 1
	}

	public void affect(Human human) {
		human.addMood(-this.mood);
		human.addEnergy(-this.energy);
		human.addHunger(-this.hunger);
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