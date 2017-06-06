package agent.place;

import agent.Human;

public class House extends Place {
	private int money;
	
	public House(int mood, int energy, int hunger) {
		super(PlaceType.HOUSE, mood, energy, hunger);
	}

	public int getMoney() {
		return this.money;
	}
	
	public void addMoney(int money) {
		this.money += money;
	}
	
	@Override
	public boolean isAccessibleTo(Human human) {
		return human.getHouse() == this;
	}
}