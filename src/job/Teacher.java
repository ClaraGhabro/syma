package job;

import action.Action;
import action.GoToPlaceAction;
import action.TeachAction;
import agent.Human;
import agent.place.PlaceType;
import context.ContextCreator;

public class Teacher extends Job {
	public Teacher() {
		this.jobType = JobType.TEACHER;
	}

	@Override
	public Action getNextStep(Human human) {
		if (ContextCreator.getPlaceAt(human.getX(), human.getY()).getType() == PlaceType.SCHOOL)
			return new TeachAction(human);
		return new GoToPlaceAction(human, PlaceType.SCHOOL);
	}
}