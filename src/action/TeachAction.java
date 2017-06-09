package action;

import java.util.ArrayList;

import agent.Human;
import context.Constants;
import context.ContextCreator;
import job.JobType;

public class TeachAction extends Action {

	public TeachAction(Human human) {
		super(human);
		this.type = new String("is teaching");
	}

	@Override
	public void initiate() {
		this.duration = Constants.schoolDuration;
	}
	
	@Override
	public void step() {
		ArrayList<Human> students = ContextCreator.getHumansWithJobAt(human.getX(), human.getY(), JobType.STUDENT);
		
		for (Human student: students) {
			human.getHouse().addMoney(Constants.schoolPrice);
			student.getHouse().addMoney(-Constants.schoolPrice);
			student.addEducation(human.getEducation() / 10);
		}
	}
}
