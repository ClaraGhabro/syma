package action;

import agent.Agent;
import agent.Human;
import context.Constants;
import context.ContextCreator;
import repast.simphony.context.Context;
import repast.simphony.util.ContextUtils;

public class ReproduceAction extends Action {
	private Human huwoman;

	public ReproduceAction(Human human, Human huwoman) {
		super(human);
		this.type = new String("Is reproducing");
		this.huwoman = huwoman;
	}
	
	@Override
	public void initiate() {
		this.duration = Constants.gestationCount;
	}

	public void addRandomHuman(int x, int y) {
		Context<Agent> context = ContextUtils.getContext(this);
		Human baby = new Human(x, y, ContextCreator.getGrid(), human, huwoman);
		context.add(baby);
		ContextCreator.getGrid().moveTo(baby, x, y);
		
		baby.getParents().add(human);
		baby.getParents().add(huwoman);
		
		this.human.getChildren().add(baby);
		this.huwoman.getChildren().add(baby);

		for (Human sibling: human.getChildren())
			sibling.getSiblings().add(baby);
		for (Human sibling: huwoman.getChildren())
			sibling.getSiblings().add(baby);
	}
	
	@Override
	public void step() {
		if (this.duration == 1)
		{
			addRandomHuman(human.getHouse().getX(), human.getHouse().getY());
			this.human.addEnergy(Constants.reproducingEnergy);
			this.huwoman.addEnergy(Constants.reproducingEnergy);

			this.human.setReproduceCount(Constants.reproduceCount);
			this.huwoman.setReproduceCount(Constants.reproduceCount);
			
			this.huwoman.resetAction();
		}
	}
}