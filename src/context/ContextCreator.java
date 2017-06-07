package context;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import repast.simphony.space.grid.Grid;
import agent.Agent;

/* TODO:
 * - generer la carte en fonction du fichier
 * - gerer la reproduction
 * - gerer l'immigration
 */
public class ContextCreator implements ContextBuilder<Agent> {
	@Override
	public Context<Agent> build(Context<Agent> context) {
		context.setId("MyNeighboursLife");
		ReadMap readMap = new ReadMap();
		
		Grid<Agent> grid = readMap.createGrid(context);
		
		return context;
	}
}