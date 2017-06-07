package job;

import action.Action;
import agent.Human;

public class Farmer extends Job {
	public Farmer(Human human) {
		super(human);
	}

	@Override
	public Action getNextStep() {
		// TODO: Si au field, HARVEST, sinon aller au plus proche FIELD à pied ou en driver si plus intéressant
		return null;
	}
}