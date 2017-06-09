package agent;

import repast.simphony.context.Context;
import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.space.grid.Grid;
import repast.simphony.util.ContextUtils;

public abstract class Agent {
	protected Grid<Agent> grid;
	protected int x;
	protected int y;
	
	public Agent(int i, int j, Grid<Agent> grid) {
		this.grid = grid;
		this.x = i;
		this.y = j;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public Grid<Agent> getGrid() {
		return grid;
	}
	
	@ScheduledMethod(start = 1, interval = 1, priority=1)
	public abstract void update();

	@ScheduledMethod(start = 1, interval = 1, priority=2)
	public abstract void step();

	protected void die() {
		Context<Agent> context = ContextUtils.getContext(this);
		context.remove(this);
	}
}