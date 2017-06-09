package action;

import agent.Human;
import context.Constants;

public class TradeAction extends Action {

	public TradeAction(Human human) {
		super(human);
		this.type = new String("is trading");
	}

	@Override
	public void initiate() {
		this.duration = Constants.tradeDuration;
	}

	@Override
	public void step() {
		this.human.getHouse().addMoney(human.getEducation() / 10);
	}
}