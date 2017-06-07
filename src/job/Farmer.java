package job;

import action.Action;
import agent.Human;

public class Farmer extends Job {
	public Farmer() {
		this.jobType = JobType.FARMER;
	}

	@Override
	public Action getNextStep(Human human) {
		// TODO: Si au field, HARVEST, sinon aller au plus proche FIELD � pied ou en driver si plus int�ressant
		return null;
	}
}
