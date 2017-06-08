package job;

import action.Action;
import action.WaitAction;
import agent.Human;

public class Seller extends Job {
	public Seller() {
		this.jobType = JobType.SELLER;
	}

	// si food < 100, va checher a aller cer un farmer, et acheter de la bouf (moins de bouffe que ce que possede le farmer, et en fonction de son bugdet)
	//stock max: 100
	
	@Override
	public Action getNextStep(Human human) {
		return new WaitAction(human);
	}
}