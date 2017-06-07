package agent.place;

import agent.Agent;
import agent.Human;
import cern.jet.random.Uniform;
import ecoSysteme.WolfAgent;
import job.Teacher;
import repast.simphony.context.Context;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;
import repast.simphony.util.ContextUtils;

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
		GridPoint gpt = grid.getLocation(this);
		Context<Object> context = ContextUtils.getContext(this);
		WolfAgent a = new WolfAgent(x,y,grid);
		a.setEnergy(energy/2);
		energy/=2;
		context.add(a);
		grid.moveTo(a, gpt.getX(), gpt.getY());
		// TODO: open si contient un TEACHER, sinon not open
	}

	@Override
	public void step() {}
}