package action;

import agent.Human;

public class WaitAction extends Action {

	public WaitAction(Human human) {
		super(human);
		this.duration = 1;
		this.type = new String("Is waiting");
	}

	@Override
	public void initiate() {}

	@Override
	public void step() {}
}
