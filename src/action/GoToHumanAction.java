package action;

import java.util.ArrayList;
import java.util.Collections;

import agent.Agent;
import agent.Human;
import agent.place.Place;
import context.ContextCreator;
import job.JobType;
import repast.simphony.space.grid.Grid;
import util.Point2D;

public class GoToHumanAction extends Action {
	private JobType jobType;
	private ArrayList<Point2D> positions;
	
	public GoToHumanAction(Human human, JobType jobType) {
		super(human);
		this.jobType = jobType;
		this.positions = new ArrayList<>();
		this.type = new String("is looking for a " + jobType.toString());
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
		ArrayList<Human> currentHumans = ContextCreator.getHumansWithJobAt(x, y, jobType);
		successors.add(currentPlace);
		
		int dxs[] = {-1, 1, 0, 0};
		int dys[] = {0, 0, -1, 1};
		
		while (successors.size() != 0 && currentHumans.size() != 0) {
			for (int i = 0; i < 4; i++) {
				int dx = dxs[i];
				int dy = dys[i];
				Place neighbour = ContextCreator.getPlaceAt(x + dx, y + dy);
				if (neighbour != null && neighbour.isAccessibleTo(human)) {
					int distance = distances[x][y] + 100 / Integer.min(Integer.min(	human.getMood() / neighbour.mood,
																					human.getEnergy() / neighbour.energy),
																					human.getHunger() / neighbour.hunger);
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
		
		if (currentHumans.size() == 0) {
			this.positions.add(new Point2D(human.getX(), human.getY()));
		}
		else {
			Point2D origin = new Point2D(human.getX(), human.getY());
			Point2D pos = new Point2D(x, y);
			while (! (pos.x == origin.x) || ! (pos.y == origin.y)) {
				this.positions.add(pos);

				if (positions.contains(predecessors[pos.x][pos.y]))
					break;
				
				pos = predecessors[pos.x][pos.y];
			}
			Collections.reverse(this.positions);
		}

		this.duration = positions.size();
	}

	@Override
	public void step() {
		// TODO : check que position contient qqch avant de recup, sinon faire autre chose
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