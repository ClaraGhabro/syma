package action;

import agent.Human;
import agent.place.Field;

public class HarvestAction extends Action {
	private Field field;

	public HarvestAction(Human human, Field field) {
		super(human);
		this.field = field;
	}

	@Override
	public void initiate() {}

	@Override
	public void step() {
		human.addQuantity(field.getAge());
		field.setAge(Math.min(field.getMinAge() - 3, human.getEducation()));
	}
}