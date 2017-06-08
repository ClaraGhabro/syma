package job;

import action.Action;
import agent.Human;

public abstract class Job {
	protected JobType jobType;

	public Job() {}

	public abstract Action getNextStep(Human human);

	public JobType getJobType() {
		return this.jobType;
	}
}
