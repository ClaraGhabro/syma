package context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import agent.Agent;
import agent.Human;
import agent.place.Place;
import job.JobType;
import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.grid.Grid;

/* TODO:
 * - generer la carte en fonction du fichier
 * - gerer la reproduction
 * - gerer l'immigration
 */
public class ContextCreator implements ContextBuilder<Agent> {
	private static Grid<Agent> grid;

	@Override
	public Context<Agent> build(Context<Agent> context) {
		context.setId("syma");
		ReadMap readMap = new ReadMap();
		
		this.grid = readMap.createGrid(context);
		
		return context;
	}
	
	public static ArrayList<Human> getHumansAt(int x, int y) {
		ArrayList<Human> result = new ArrayList<>();
		for (Object obj: grid.getObjectsAt(x, y)) {
			if (obj instanceof Human) {
				result.add((Human) obj);
			}
		}
		return result;
	}
	
	public static ArrayList<Human> getHumansWithJobAt(int x, int y, JobType job) {
		ArrayList<Human> humans = getHumansAt(x, y);
		ArrayList<Human> result = new ArrayList<>();
		for (Human human: humans) {
			if (human.getJob().getJobType() == job)
				result.add(human);
		}
		return result;
	}
	
	public static Place getPlaceAt(int x, int y) {
		for (Object obj: grid.getObjectsAt(x, y))
			if (obj instanceof Place)
				return ((Place) obj);
		return null;
	}
	
	public static String getRandomName() {
		List<String> names = Constants.names;
		Random rnd = new Random();
		return names.get(rnd.nextInt(names.size()));
	}

	public static Grid<Agent> getGrid() {
		return grid;
	}
}