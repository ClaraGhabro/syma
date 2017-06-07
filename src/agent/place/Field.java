package agent.place;

import agent.Agent;
import agent.Human;
import job.Farmer;
import repast.simphony.space.grid.Grid;

public class Field extends Place {
	private static int maxAge;
	private static int minAge;
	private int age;

	public Field(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger) {
		super(i, j, grid, PlaceType.FIELD, mood, energy, hunger);
		this.age = 0;
	}

	public void setMaxAge(int age) {
		Field.maxAge = age;
	}

	public void setMinAge(int age) {
		Field.minAge = age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
	@Override
	public PlaceType getType() {
		return age > minAge ? PlaceType.FIELD: PlaceType.LAND;
	}

	@Override
	public boolean isAccessibleTo(Human human) {
		return human.getJob() instanceof Farmer;
	}

	@Override
	public void update() {
		this.age = (age + 1) % maxAge;
	}

	@Override
	public void step() {}
}