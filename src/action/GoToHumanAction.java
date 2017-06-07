package action;

import agent.Human;
import agent.place.Place;
import job.JobType;

public class GoToHumanAction extends Action {
	private Human destHuman;
	private JobType jobType;
	private int x;
	private int y;
	
	public GoToHumanAction(Human human, JobType jobType) {
		super(human);
		this.destHuman = null;
		this.jobType = jobType;
	}

	@Override
	public void initiate() {
		// TODO: Trouver l'humain le plus proche qui a le JOB, et retenir le chemin
	}

	@Override
	public void step() {
		// TODO: Avancer jusqu'à l'humain
	}
}