package job;

import action.Action;
import agent.Human;

public class Student extends Job {
	public Student() {
		this.jobType = JobType.STUDENT;
	}

	@Override
	public Action getNextStep() {
		// TODO: Si assez vieux, goGetHouse, sinon si � l'�cole LearnAction, sinon goToSchool, sinon House
		return null;
	}

}
