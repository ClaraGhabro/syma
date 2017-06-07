package job;

import action.Action;
import agent.Human;

public class Teacher extends Job {
	public Teacher() {
		this.jobType = JobType.TEACHER;
	}

	@Override
	public Action getNextStep(Human human) {
		// TODO: GoToPlace �cole la plus proche, sinon WAIT
		return null;
	}
}
