package action;

import agent.Human;
import context.Constants;
import job.JobType;

public class BuyFoodAction extends Action {
	private Human seller;
	
	public BuyFoodAction(Human human, Human seller) {
		super(human);
		this.seller = seller;
		this.type = new String("is buying food");
	}

	@Override
	public void initiate() {
		this.duration = 1;
	}

	@Override
	public void step() {
		double price = (seller.getEducation()) / 10;
		
		if (seller.getJob().getJobType() == JobType.FARMER)
			price *= Constants.farmerPrice;
		
		int quantity = (int) Math.min(human.getHouse().getMoney() / price / 2, seller.getHunger() - 100);
		if (seller.getJob().getJobType() != JobType.FARMER)
			quantity = Math.min(quantity, 100 - human.getHunger());
		
		if (quantity <= 0)
			return;
		
		this.human.getHouse().addFood(quantity);
		this.seller.getHouse().addFood(-quantity);
		
		this.human.getHouse().addMoney(price * quantity);
		this.seller.getHouse().addMoney(- (price * quantity));
	}
}