package agent.place;

import agent.Human;

public class Road extends Place {
	public Road(int mood, int energy, int hunger) {
		super(PlaceType.ROAD, mood, energy, hunger);
	}
	
	public boolean isAccessibleTo(Human human) {
		return human.getWork() instanceof Driver;
	}
}