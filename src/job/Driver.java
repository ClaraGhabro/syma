package job;

import action.Action;
import agent.Human;

public class Driver extends Job {
	public Driver(Human human) {
		super(human);
		this.jobType = JobType.DRIVER;
	}

	@Override
	public Action getNextStep() {
		// TODO: Si à la destination, pose l'humain, si pas à la destination, emmene l'humain, si pas d'humain et si hors d'une route, va sur la route, sinon va au barycentre des positions
		return null;
	}

}
