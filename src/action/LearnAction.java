package action;

import agent.Human;

public class LearnAction extends Action {

	public LearnAction(Human human) {
		super(human);
	}

	@Override
	public void initiate() {
		// TODO: Calculer la durée d'éducation en fonction de l'argent		
	}

	@Override
	public void step() {
		// TODO: échanger de l'argent contre de l'éducation auprès du professeur
	}
}
