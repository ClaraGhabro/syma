package job;

import action.Action;
import agent.Human;

public class Teacher extends Job {

	public Teacher(Human human) {
		super(human);
		this.jobType = JobType.TEACHER;
	}

	@Override
	public Action getNextStep() {
		// TODO: GoToPlace �cole la plus proche, sinon WAIT
		return null;
	}
}