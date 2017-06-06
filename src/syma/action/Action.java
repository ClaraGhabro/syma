package syma.action;

import syma.agent.Human;

public abstract class Action {
	private int duration;
	private int mood;
	private int energy;
	private int hungry;
	
	public void impact(Human human) {
		human.setMood(this.mood);
		human.setEnergy(this.energy);
		human.setHungry(this.hungry);
	}
}