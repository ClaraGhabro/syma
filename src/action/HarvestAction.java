package action;

import agent.Human;
import agent.place.Field;

public class HarvestAction extends Action {
	private Field field;

	public HarvestAction(Human human, Field field) {
		super(human);
		this.field = field;
		this.type = new String("Is harversting");
	}

	@Override
	public void initiate() {
		this.duration = 1;
	}

	@Override
	public void step() {
		human.addHunger(field.getAge());
		field.setAge(Math.min(field.getMinAge() - 3, human.getEducation()));
	}
}