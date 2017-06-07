package job;

import action.Action;
import agent.Human;

public class Driver extends Job {
	public Driver() {
		this.jobType = JobType.DRIVER;
	}

	@Override
	public Action getNextStep(Human human) {
		// TODO: Si � la destination, pose l'humain, si pas � la destination, emmene l'humain, si pas d'humain et si hors d'une route, va sur la route, sinon va au barycentre des positions
		return null;
	}

}
