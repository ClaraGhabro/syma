package job;

import action.Action;
import agent.Human;

public class Student extends Job {
	public Student(Human human) {
		super(human);
	}

	@Override
	public Action getNextStep() {
		// TODO: Si assez vieux, goGetHouse, sinon si à l'école LearnAction, sinon goToSchool, sinon House
		return null;
	}

}
