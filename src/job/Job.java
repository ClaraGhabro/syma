package job;

import action.Action;
import agent.Human;

public abstract class Job {
	protected Human human;
	protected JobType jobType;

	public Job(Human human) {
		this.human = human;
	}

	public JobType getJobType() {
		return this.jobType;
	}

	public abstract Action getNextStep();
}