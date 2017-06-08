package action;

import java.util.ArrayList;
import java.util.Collections;

import com.sun.javafx.geom.Point2D;

import agent.Agent;
import agent.Human;
import agent.place.Place;
import agent.place.PlaceType;
import context.ContextCreator;
import repast.simphony.space.grid.Grid;

public class GoToPlaceAction extends Action {
	private PlaceType type;
	private ArrayList<Point2D> positions;

	public GoToPlaceAction(Human human, PlaceType placeType) {
		super(human);
		this.type = placeType;
		this.positions = new ArrayList<>();
	}

	@Override
	public void initiate() {
		Grid<Agent> grid = ContextCreator.getGrid();
		
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
		int dxs[] = {0, 0, -1, 1};
		int dys[] = {-1, 1, 0, 0};

		for (int dx: dxs)
			System.out.println(dx);
		
		while (successors.size() != 0 && currentPlace.getType() != type) {
			for (int dx: dxs) {
				System.out.println(dx);
				for (int dy: dys) {
					System.out.println(dy);
					Place neighbour = ContextCreator.getPlaceAt(x + dx, y + dy);
					if (neighbour != null) {
						int distance = distances[x][y] + 100 / Integer.min(Integer.min(	human.getMood() / neighbour.getMood(),
																						human.getEnergy() / neighbour.getEnergy()),
																						human.getHunger() / neighbour.getHunger());
						if (distance < distances[x + dx][y + dx]) {
							distances[x + dx][y + dy] = distance;
							predecessors[x + dx][y + dy] = new Point2D(x, y);
							successors.add(neighbour);
						}
					}
				}
			}
			
			int min = Integer.MAX_VALUE;
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
		
		if (currentPlace.getType() != type) {
			this.positions.add(new Point2D(human.getX(), human.getY()));
		}
		else {
			Point2D origin = new Point2D(human.getX(), human.getY());
			Point2D pos = new Point2D(x, y);
			while (! (pos.x == origin.x) || ! (pos.y == origin.y)) {
				this.positions.add(pos);
				pos = predecessors[(int) pos.x][(int) pos.y];
			}
			Collections.reverse(this.positions);
		}
		
		this.duration = positions.size();
	}

	@Override
	public void step() {
		Point2D position = positions.get(0);

		// DEBUG
		System.out.println(positions.size());
		System.out.println(positions);
		System.out.println("Go to");
		System.out.println(position);
		System.out.println("From");
		System.out.println(new Point2D(human.getX(), human.getY()));
		System.out.println(" ");

		if (ContextCreator.getPlaceAt((int) position.x, (int) position.y).isAccessibleTo(human)) {
			ContextCreator.getGrid().moveTo(human, (int) positions.get(0).x, (int) positions.get(0).y);
			positions.remove(0);
		}
	}
}