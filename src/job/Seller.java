package job;

import action.Action;
import action.WaitAction;
import agent.Human;

public class Seller extends Job {
	public Seller() {
		this.jobType = JobType.SELLER;
	}

	@Override
	public Action getNextStep(Human human) {
		return new WaitAction(human);
	}
}
