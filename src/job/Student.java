package job;

import action.Action;
import action.GoToPlaceAction;
import action.WaitAction;
import agent.Human;
import agent.place.PlaceType;
import context.Constants;
import context.ContextCreator;

public class Student extends Job {
	public Student() {
		this.jobType = JobType.STUDENT;
	}

	@Override
	public Action getNextStep(Human human) {
		if (human.getAge() > Constants.maxAge)
			return human.goGetAdulthood();
		if (ContextCreator.getPlaceAt(human.getX(), human.getY()).getType() == PlaceType.SCHOOL)
			return new WaitAction(human, Constants.schoolDuration);
		return new GoToPlaceAction(human, PlaceType.SCHOOL);
	}
}