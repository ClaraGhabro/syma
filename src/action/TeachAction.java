package action;

import agent.Human;
import context.Constants;
import context.ContextCreator;
import job.JobType;

public class TeachAction extends Action {

	public TeachAction(Human human) {
		super(human);
		this.type = new String("Is teaching");
	}

	@Override
	public void initiate() {
		this.duration = Constants.schoolDuration;
	}
	
	public int getNbStudents() {
		return ContextCreator.getHumansWithJobAt(human.getX(), human.getY(), JobType.STUDENT).size();
	}

	@Override
	public void step() {
		int nbStudents = getNbStudents();
		this.human.getHouse().addMoney(Constants.schoolPrice * nbStudents);
	}
}
