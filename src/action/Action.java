package action;

import agent.Human;

public abstract class Action {
	protected Human human;
	protected int duration;
	protected String type;
	
	@Override
	public String toString() {
		return type;
	}
	
	public Action(Human human) {
		this.human = human;
	}
	
	public void update() {
		this.duration--;
	}
	public int getDuration() {
		return duration;
	}
	
	public abstract void initiate();
	public abstract void step();
}