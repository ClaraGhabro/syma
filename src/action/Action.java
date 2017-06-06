package action;

import agent.HumanAgent;

public abstract class Action {
	private int duration;
	private int mood;
	private int energy;
	private int hungry;
	
	public void impact(HumanAgent human) {
		human.setMood(this.mood);
		human.setEnergy(this.energy);
		human.setHungry(this.hungry);
	}
}