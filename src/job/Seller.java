package job;

import action.Action;
import agent.Human;

public class Seller extends Job {
	public Seller(Human human) {
		super(human);
	}

	@Override
	public Action getNextStep() {
		// TODO: Va � un hungry human, sinon random
		return null;
	}

}