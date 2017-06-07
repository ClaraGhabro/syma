package action;

import agent.Human;

public class WaitAction extends Action {
	public WaitAction(Human human, int duration) {
		super(human);
		this.duration = duration;
	}

	@Override
	public void initiate() {}

	@Override
	public void step() {
		this.duration--;
	}
}
