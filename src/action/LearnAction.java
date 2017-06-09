package action;

import agent.Human;

public class LearnAction extends Action {

	public LearnAction(Human human, int duration) {
		super(human);
		this.duration = duration;
		this.type = new String("is learning");
	}

	@Override
	public void initiate() {}

	@Override
	public void step() {}
}
