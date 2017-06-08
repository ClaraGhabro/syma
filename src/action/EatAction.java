package action;

import agent.Human;
import context.Constants;

public class EatAction extends Action {	
	public EatAction(Human human) {
		super(human);
		this.type = new String("Is eating food");
	}

	@Override
	public void initiate() {
		this.duration = (100 - human.getHunger()) / Constants.foodHunger;
	}

	@Override
	public void step() {
		this.human.addHunger(Constants.foodHunger);
	}
}