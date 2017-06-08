package action;

import agent.Human;
import agent.place.PlaceType;
import context.Constants;
import context.ContextCreator;

public class RelaxAction extends Action {
	public RelaxAction(Human human) {
		super(human);
	}

	@Override
	public void initiate() {
		this.duration = 1;
	}

	@Override
	public void step() {
		if (ContextCreator.getPlaceAt(human.getX(),  human.getY()).getType() == PlaceType.PARK)
			human.addMood(Constants.moodHappy);
	}
}
