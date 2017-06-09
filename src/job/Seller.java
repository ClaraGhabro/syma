package job;

import java.util.ArrayList;

import action.Action;
import action.BuyFoodAction;
import action.GoToHumanAction;
import action.GoToPlaceAction;
import action.RelaxAction;
import agent.Human;
import agent.place.PlaceType;
import context.ContextCreator;

public class Seller extends Job {
	public Seller() {
		this.jobType = JobType.SELLER;
	}

	@Override
	public Action getNextStep(Human human) {
		if (human.getHunger() < 200) {
			ArrayList<Human> farmers = ContextCreator.getHumansWithJobAt(human.getX(), human.getY(), JobType.FARMER);
			if (farmers.isEmpty())
				return new GoToHumanAction(human, JobType.FARMER);
			else
				return new BuyFoodAction(human, farmers.get(0));
			/*
			if (farmers.size() > 0)
				return new BuyFoodAction(human, farmers.get(0));
			else
				return new GoToHumanAction(human, JobType.FARMER);
*/		}
		else {
			if (ContextCreator.getPlaceAt(human.getX(),  human.getY()).getType() == PlaceType.PARK)
				return new RelaxAction(human);
			else
				return new GoToPlaceAction(human, PlaceType.PARK);
		}
	}
}