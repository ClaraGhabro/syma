package agent.place;

import agent.Human;

public class School extends Place {
	private boolean open;
	
	public School(int mood, int energy, int hunger) {
		super(PlaceType.SCHOOL, mood, energy, hunger);
		this.open = false;
	}
	
	public boolean isAccessibleTo(Human human) {
		return open || human.getWork() instanceof Teacher;
	}
}