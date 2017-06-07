package agent.place;

import agent.Agent;
import agent.Human;
import job.Teacher;
import repast.simphony.space.grid.Grid;

public class School extends Place {
	private boolean open;

	public School(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger) {
		super(i, j, grid, PlaceType.SCHOOL, mood, energy, hunger);
		this.open = false;
	}

	public boolean isAccessibleTo(Human human) {
		return open || human.getJob() instanceof Teacher;
	}

	@Override
	public void update() {
		
		// TODO: open si contient un TEACHER, sinon not open
	}

	@Override
	public void step() {}
}