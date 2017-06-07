package job;

import action.Action;
import action.TradeAction;
import agent.Human;

public class Banker extends Job {
	public Banker(Human human) {
		super(human);
	}

	@Override
	public Action getNextStep() {
		return new TradeAction(human);
	}
}