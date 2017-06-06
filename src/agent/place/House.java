package agent.place;

import agent.Agent;
import agent.Human;
import repast.simphony.space.grid.Grid;

public class House extends Place {
	private int money;

	public House(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger) {
		super(i, j, grid, PlaceType.HOUSE, mood, energy, hunger);
	}

	public int getMoney() {
		return this.money;
	}

	public void addMoney(int money) {
		this.money += money;
	}

	@Override
	public boolean isAccessibleTo(Human human) {
		return human.getHouse() == this;
	}

	@Override
	public void update() {}

	@Override
	public void step() {}
}