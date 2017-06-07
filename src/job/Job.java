package job;

import action.Action;
import agent.Human;

public abstract class Job {
	protected Human human;

	public Job(Human human) {
		this.human = human;
	}
	public abstract Action getNextStep();
}