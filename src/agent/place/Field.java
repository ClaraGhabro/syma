package agent.place;

import agent.Agent;
import agent.Human;
import context.Constants;
import job.Farmer;
import job.JobType;
import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.util.ContextUtils;

public class Field extends Place {
	private int maxAge;
	private int minAge;
	private int age;
	private boolean mature;

	public Field(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger) {
		super(i, j, grid, PlaceType.FIELD, mood, energy, hunger);
		this.age = 0;
		this.maxAge = RandomHelper.createNormal(30, 5).nextInt();
		this.minAge = RandomHelper.createNormal(15, 2).nextInt();
		this.mature = false;
	}
	
	public Field(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger, int age) {
		super(i, j, grid, PlaceType.FIELD, mood, energy, hunger);
		this.age = 0;
		this.maxAge = RandomHelper.createNormal(30, 5).nextInt();
		this.minAge = RandomHelper.createNormal(15, 2).nextInt();
		this.age = age;
		this.mature = age >= minAge && age <= maxAge;
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
		return human.getJob().getJobType() == JobType.FARMER || human.getJob().getJobType() == JobType.SELLER;
	}

	@Override
	public void update() {
		if (RunEnvironment.getInstance().getCurrentSchedule().getTickCount() % Constants.fieldCount == 0)
			this.age = (age + 1) % maxAge;
		boolean newMature = age >= minAge && age <= maxAge;
		if (newMature != mature) {
			Context<Agent> context = ContextUtils.getContext(this);
			
			Field f = new Field(this.x, this.y, this.grid, this.getMood(), this.getEnergy(), this.getHunger(), this.age);
			context.add(f);
			this.grid.moveTo(f,  this.x, this.y);
			context.remove(this);
			
		}
				
	}

	@Override
	public void step() {}
}