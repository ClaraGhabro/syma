package action;

import agent.Human;

public abstract class Action {
	private int duration;
	private int mood;
	private int energy;
	private int hunger;
	
	public abstract void initiate(Human human);
}