package agent;

import repast.simphony.engine.schedule.ScheduledMethod;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.util.ContextUtils;

import java.awt.Color;

import cern.jet.random.Uniform;
import repast.simphony.context.Context;

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
		System.err.println("DIE");
	}

	/*
	protected void move() {
		Uniform u = RandomHelper.createUniform();
		double xx = u.nextDouble();
		double yy = u.nextDouble();
		int x2 = (int) Math.round((xx * 2) - 1);
		int y2= (int) Math.round((yy * 2) - 1);
		 x += x2;
		 y += y2;
		 
		 grid.moveTo(this, x, y);
		}
	}
	*/
}