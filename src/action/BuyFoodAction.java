package action;

import agent.Human;

public class BuyFoodAction extends Action {
	public BuyFoodAction(Human human) {
		super(human);
	}

	@Override
	public void initiate() {
		// TODO: Calculer la quantit� de nourriture achetable (minimum entre hunger du vendeur au dessus de 100 et argent disponible)
	}

	@Override
	public void step() {
		// TODO: Acheter la nourriture => transf�rer de l'hunger du vendeur � l'humain, inverse pour l'argent en fonction de l'�ducation
	}
}