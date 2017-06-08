package action;

import agent.Human;
import context.Constants;

public class LearnAction extends Action {

	public LearnAction(Human human) {
		super(human);
		this.type = new String("Is in class");
	}

	@Override
	public void initiate() {
		this.duration = Math.min(Constants.schoolDuration, (int)(human.getHouse().getMoney() / Constants.schoolPrice));
	}

	@Override
	public void step() {
		this.human.getHouse().addMoney(-Constants.schoolPrice);
	}
}