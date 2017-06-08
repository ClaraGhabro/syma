package agent.place;

import agent.Agent;
import agent.Human;
import job.Farmer;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;

public class Field extends Place {
	private int maxAge;
	private int minAge;
	private int age;

	public Field(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger) {
		super(i, j, grid, PlaceType.FIELD, mood, energy, hunger);
		this.age = 0;
		this.maxAge = RandomHelper.createNormal(30, 5).nextInt();
		this.minAge = RandomHelper.createNormal(15, 2).nextInt();
	}

	public void setMaxAge(int age) {
		this.maxAge = age;
	}

	public void setMinAge(int age) {
		this.minAge = age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
	public int getMinAge() {
		return minAge;
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