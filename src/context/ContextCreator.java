package context;

import repast.simphony.context.Context;
import repast.simphony.dataLoader.ContextBuilder;
import agent.Agent;

/* TODO:
 * - generer la carte en fonction du fichier
 * - gerer la reproduction
 * - gerer l'immigration
 */
public class ContextCreator implements ContextBuilder<Agent> {
	@Override
	public Context<Agent> build(Context<Agent> context) {
		
		return context;
	}
}