package agent.place;

import agent.Agent;
import agent.Human;
import job.Driver;
import repast.simphony.space.grid.Grid;

public class Road extends Place {
	public Road(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger) {
		super(i, j, grid, PlaceType.ROAD, mood, energy, hunger);
	}

	public boolean isAccessibleTo(Human human) {
		return human.getJob() instanceof Driver;
	}

	@Override
	public void update() {}

	@Override
	public void step() {}
}