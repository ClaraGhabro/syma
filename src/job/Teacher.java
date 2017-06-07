package job;

import action.Action;
import agent.Human;

public class Teacher extends Job {

	public Teacher(Human human) {
		super(human);
	}

	@Override
	public Action getNextStep() {
		// TODO: GoToPlace école la plus proche, sinon WAIT
		return null;
	}
}