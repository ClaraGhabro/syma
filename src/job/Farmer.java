package job;

import action.Action;
import action.GoToPlaceAction;
import action.HarvestAction;
import action.WaitAction;
import agent.Human;
import agent.place.Field;
import agent.place.PlaceType;
import context.ContextCreator;

public class Farmer extends Job {
	public Farmer() {
		this.jobType = JobType.FARMER;
	}

	@Override
	public Action getNextStep(Human human) {
		if (human.getHouse().getFood() > 300)
			return new WaitAction(human, 1);
		if (ContextCreator.getPlaceAt(human.getX(), human.getY()).getType() == PlaceType.FIELD)
			return new HarvestAction(human, (Field)ContextCreator.getPlaceAt(human.getX(), human.getY()));
		return new GoToPlaceAction(human, PlaceType.FIELD);
	}
}
