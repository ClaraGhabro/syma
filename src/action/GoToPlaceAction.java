package action;

import agent.Human;
import agent.place.Place;
import agent.place.PlaceType;

public class GoToPlaceAction extends Action {
	private Place place;
	private PlaceType type;
	
	public GoToPlaceAction(Human human, PlaceType placeType) {
		super(human);
		this.place = null;
		this.type = placeType;
	}

	@Override
	public void initiate() {
		// TODO: Trouver le lieu le plus proche correspondant à PlaceType
	}

	@Override
	public void step() {
		// TODO: Avancer jusqu'au lieu en question
	}
}