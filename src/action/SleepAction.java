package action;

import agent.Human;
import context.Constants;

public class SleepAction extends Action {
	public SleepAction(Human human) {
		super(human);
		this.type = new String("Is sleeping");
	}

	@Override
	public void initiate() {
		this.duration = (Constants.maxSleepHappy - human.getEnergy()) / Constants.sleepEnergy;		
	}

	@Override
	public void step() {
		this.human.addEnergy(Constants.sleepEnergy);
	}
}