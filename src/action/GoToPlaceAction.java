package action;

import java.util.ArrayList;
import java.util.Collections;

import agent.Agent;
import agent.Human;
import agent.place.Place;
import agent.place.PlaceType;
import context.ContextCreator;
import repast.simphony.space.grid.Grid;
import util.Point2D;

public class GoToPlaceAction extends Action {
	private PlaceType placeType;
	private ArrayList<Point2D> positions;

	public GoToPlaceAction(Human human, PlaceType placeType) {
		super(human);
		this.placeType = placeType;
		this.positions = new ArrayList<>();
		this.type = new String("Is going to a " + placeType.toString());
	}

	@Override
	public void initiate() {
		Grid<Agent> grid = ContextCreator.getGrid();
		this.positions = new ArrayList<>();
		
		int width = grid.getDimensions().getWidth();
		int height = grid.getDimensions().getHeight();
		
		int 				distances[][] = new int[width][height];
		Point2D 			predecessors[][] = new Point2D[width][height];
		ArrayList<Place> 	successors = new ArrayList<>();

		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				distances[i][j] = Integer.MAX_VALUE;

		int x = human.getX();
		int y = human.getY();
		
		distances[x][y] = 0;
		Place currentPlace = ContextCreator.getPlaceAt(x, y);
		successors.add(currentPlace);
		
		int dxs[] = {-1, 1, 0, 0};
		int dys[] = {0, 0, -1, 1};
		
		while (successors.size() != 0 && currentPlace.getType() != placeType) {
			for (int i = 0; i < 4; i++) {
				int dx = dxs[i];
				int dy = dys[i];
				Place neighbour = ContextCreator.getPlaceAt(x + dx, y + dy);
				if (neighbour != null && neighbour.isAccessibleTo(human)) {
					int distance = distances[x][y] + (int) (100 / Float.min(Float.min(	human.getMood() 	/ neighbour.getMood(),
																						human.getEnergy() 	/ neighbour.getEnergy()),
																						human.getHunger() 	/ neighbour.getHunger()));
					if (x + dx >= 0 && x + dx < width
							&& y + dy >= 0 && y + dy < height
							&& distance < distances[x + dx][y + dy]) {
						distances[x + dx][y + dy] = distance;
						predecessors[x + dx][y + dy] = new Point2D(x, y);
						successors.add(neighbour);
					}
				}
			}

			int min = Integer.MAX_VALUE / 4;
			int iMin = 0;
			for (int i = 0; i < successors.size(); i++) {
				Place p = successors.get(i);
				if (distances[p.getX()][p.getY()] < min) {
					min = distances[p.getX()][p.getY()];
					iMin = i;
				}
			}
			currentPlace = successors.get(iMin);
			x = currentPlace.getX();
			y = currentPlace.getY();
			successors.remove(iMin);
		}
		
		if (currentPlace.getType() != placeType) {
			this.positions.add(new Point2D(human.getX(), human.getY()));
		}
		else {
			Point2D origin = new Point2D(human.getX(), human.getY());
			Point2D pos = new Point2D(x, y);
			while (! (pos.x == origin.x) || ! (pos.y == origin.y)) {
				this.positions.add(pos);
				pos = predecessors[pos.x][pos.y];
			}
			Collections.reverse(this.positions);
		}

		this.duration = positions.size();
	}

	@Override
	public void step() {
		Point2D position = positions.get(0);

		if (ContextCreator.getPlaceAt(position.x, position.y).isAccessibleTo(human)) {
			ContextCreator.getGrid().moveTo(human, position.x, position.y);

			positions.remove(0);
			human.setX(position.x);
			human.setY(position.y);
		}
		else {
			this.duration = 0;
			this.positions = new ArrayList<>();
		}
	}
}