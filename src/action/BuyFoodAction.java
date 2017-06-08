package action;

import agent.Human;
import context.Constants;

public class BuyFoodAction extends Action {
	private Human seller;
	
	public BuyFoodAction(Human human, Human seller) {
		super(human);
		this.type = new String("Is buying food");
	}

	@Override
	public void initiate() {
		this.duration = Math.min(
				Math.min((int)human.getHouse().getMoney() / (int)seller.getPrice(), seller.getFoodQuantity()),
				(80 - human.getEnergy()) / Constants.foodHunger
			);
	}

	@Override
	public void step() {
		this.human.getHouse().addMoney(-seller.getPrice());
		this.seller.getHouse().addMoney(seller.getPrice());
	}
}