package agent.place;

import agent.Human;

public abstract class Place {
	private PlaceType type;
	private int mood;
	private int energy;
	private int hunger;

	public Place(PlaceType type, int mood, int energy, int hunger) {
		this.type = type;
		this.mood = mood;
		this.energy = energy;
		this.hunger = hunger;
	}
	
	public void affect(Human human) {
		human.addMood(this.mood);
		human.addEnergy(this.energy);
		human.addHunger(this.hunger);
	}
	
	public PlaceType getType() {
		return this.type;
	}
	
	public boolean isAccessibleTo(Human human) {
		return this.type != PlaceType.WATER;
	}
}