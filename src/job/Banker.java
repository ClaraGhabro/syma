package job;

import action.Action;
import action.TradeAction;
import agent.Human;

public class Banker extends Job {
	public Banker(Human human) {
		super(human);
		this.jobType = JobType.BANKER;
	}

	@Override
	public Action getNextStep() {
		return new TradeAction(human);
	}
}