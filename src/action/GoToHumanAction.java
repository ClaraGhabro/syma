package action;

import agent.Human;
import agent.place.Place;

public class GoToHumanAction extends Action {
	private Human destHuman;
	private int x;
	private int y;
	
	public GoToPersonAction(Human human, Human destHuman) {
		super(human);
		this.destHuman = destHuman;
	}
}