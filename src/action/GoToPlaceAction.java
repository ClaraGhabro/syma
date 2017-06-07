package action;

import java.util.ArrayList;

import com.sun.javafx.geom.Point2D;

import agent.Agent;
import agent.Human;
import agent.place.Place;
import agent.place.PlaceType;
import context.ContextCreator;
import repast.simphony.space.grid.Grid;

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
		Grid<Agent> grid = place.getGrid();
		
		int width = grid.getDimensions().getWidth();
		int height = grid.getDimensions().getHeight();
		
		int 				distances[][] = new int[width][height];
		Point2D 			predecessors[][] = new Point2D[width][height];
		ArrayList<Point2D> 	successors = new ArrayList<>();

		int x = human.getX();
		int y = human.getY();
		distances[x][y] = 0;
		while (ContextCreator.getPlaceAt(x,  y).getType() != type) {
			
		}

		// TODO: Trouver le lieu le plus proche correspondant à PlaceType
	}

	@Override
	public void step() {
		// TODO: Avancer jusqu'au lieu en question
	}
}