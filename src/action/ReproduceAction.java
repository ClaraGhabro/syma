package action;

import agent.Human;

public class ReproduceAction extends Action {
	public ReproduceAction(Human human) {
		super(human);
	}

	@Override
	public void initiate() {}

	@Override
	public void step() {
		// TODO: faire apparaitre un student � la maison, diminuer l'�nergie
	}
}