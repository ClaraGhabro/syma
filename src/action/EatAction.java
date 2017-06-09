package action;

import agent.Human;
import context.Constants;

public class EatAction extends Action {
	public EatAction(Human human) {
		super(human);
		this.type = new String("is eating");
	}

	@Override
	public void initiate() {
		this.duration = (int) Math.min(human.getHouse().getFood(), (Constants.maxFoodHappy - human.getHunger()) / Constants.hungerPerUnit);
	}

	@Override
	public void step() {
		human.addHunger(Constants.hungerPerUnit);
		human.getHouse().addFood(-1);
	}
}