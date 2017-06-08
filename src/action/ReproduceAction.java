package action;

import agent.Agent;
import agent.Human;
import context.Constants;
import context.ContextCreator;
import repast.simphony.context.Context;
import repast.simphony.util.ContextUtils;

public class ReproduceAction extends Action {
	private Human human2;

	public ReproduceAction(Human human, Human human2) {
		super(human);
		this.type = new String("Is reproducing");
		this.human2 = human2;
	}
	
	@Override
	public void initiate() {}

	public void addRandomHuman(int x, int y) {
		Context<Agent> context = ContextUtils.getContext(this);
		Human baby = new Human(x, y, ContextCreator.getGrid(), human, human2);
		context.add(baby);
		ContextCreator.getGrid().moveTo(baby, x, y);
		this.human.getChildren().add(baby);
		this.human2.getChildren().add(baby);
	}
	
	@Override
	public void step() {
		if (this.duration == 1)
		{
			addRandomHuman(human.getX(), human.getY());
			this.human.addEnergy(Constants.reproducingEnergy);
			this.human2.addEnergy(Constants.reproducingEnergy);
			this.human2.resetAction();
		}
	}
}