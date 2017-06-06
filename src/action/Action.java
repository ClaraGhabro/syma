package action;

import agent.Human;

public abstract class Action {
	private int duration;
	private int mood;
	private int energy;
	private int hunger;
	
	public void impact(Human human) {
		human.addMood(this.mood);
		human.addEnergy(this.energy);
		human.addHunger(this.hunger);
	}
}