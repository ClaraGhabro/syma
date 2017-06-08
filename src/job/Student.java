package job;

import action.Action;
import action.GoToPlaceAction;
import action.LearnAction;
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
		if (ContextCreator.getPlaceAt(human.getX(), human.getY()).getType() == PlaceType.SCHOOL)
			return new LearnAction(human);
		return new GoToPlaceAction(human, PlaceType.SCHOOL);
	}
}
