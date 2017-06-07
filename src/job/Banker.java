package job;

import action.Action;
import action.TradeAction;
import agent.Human;

public class Banker extends Job {
	public Banker() {
		this.jobType = JobType.BANKER;
	}

//	public Action getNextStep(Human human) {
//		return new TradeAction(human);
	public Action getNextStep() {
		return null;//new TradeAction(human);
	}
}
