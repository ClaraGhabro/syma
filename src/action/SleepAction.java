package action;

import agent.Human;

public class SleepAction extends Action {
	public SleepAction(Human human) {
		super(human);
	}

	@Override
	public void initiate() {
		this.duration = (100 - human.getEnergy()) / 10 + 1;		
	}

	@Override
	public void step() {
		this.human.addEnergy(10);
		this.duration--;
	}
}