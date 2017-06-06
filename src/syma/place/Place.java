package syma.place;

import syma.agent.Human;

public abstract class Place {
	private int mood;
	private int energy;
	private int hungry;

	public Place(int mood, int energy, int hungry) {
		this.mood = mood;
		this.energy = energy;
		this.hungry = hungry;
	}
	void affect(Human human) {
		human.setMood(this.mood);
		human.setEnergy(this.energy);
		human.setHungry(this.hungry);
	}
}
