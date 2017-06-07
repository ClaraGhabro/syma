package job;

import action.Action;
import agent.Human;

public class Job {
	protected JobType jobType;

	public Job() {}

	public Action getNextStep() {
		return null;
	}

	public JobType getJobType() {
		return this.jobType;
	}

}
