package action;

import agent.Human;

public class LearnAction extends Action {

	public LearnAction(Human human) {
		super(human);
	}

	@Override
	public void initiate() {
		// TODO: Calculer la dur�e d'�ducation en fonction de l'argent		
	}

	@Override
	public void step() {
		// TODO: �changer de l'argent contre de l'�ducation aupr�s du professeur
	}
}
