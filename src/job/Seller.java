package job;

import action.Action;
import agent.Human;

public class Seller extends Job {
	public Seller(Human human) {
		super(human);
		this.jobType = JobType.SELLER;
	}

	@Override
	public Action getNextStep() {
		// TODO: Va à un hungry human, sinon random
		return null;
	}

}