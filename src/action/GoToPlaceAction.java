package action;

import agent.Human;
import agent.place.Place;

public class GoToPlaceAction extends Action {
	private Place place;
	
	public GoToPlaceAction(Human human, Place place) {
		super(human);
		this.place = place;
	}
}