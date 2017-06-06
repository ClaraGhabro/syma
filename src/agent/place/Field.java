package agent.place;

import agent.Human;

public class Field extends Place {
	private static int maxAge;
	private static int minAge;
	private int age;
	
	public Field(int mood, int energy, int hunger) {
		super(PlaceType.FIELD, mood, energy, hunger);
		this.age = 0;
	}

	public void setMaxAge(int age) {
		this.maxAge = age;
	}
	
	public void setMinAge(int age) {
		this.minAge = age;
	}
	
	@Override
	public PlaceType getType() {
		return age > minAge ? PlaceType.FIELD: PlaceType.LAND;
	}
	
	@Override
	public boolean isAccessibleTo(Human human) {
		return human.getWork() instanceof Farmer;
	}
}