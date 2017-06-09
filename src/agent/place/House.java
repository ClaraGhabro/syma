package agent.place;

import java.util.ArrayList;

import agent.Agent;
import agent.Human;
import repast.simphony.space.grid.Grid;

public class House extends Place {
	private double money;
	private int food;
	private ArrayList<Human> inhabitants;

	public House(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger) {
		super(i, j, grid, PlaceType.HOUSE, mood, energy, hunger);
		this.inhabitants = new ArrayList<>();
	}

	public double getMoney() {
		return this.money;
	}
	
	public int getFood() {
		return this.food;
	}

	public void addMoney(double money) {
		this.money += money;
	}
	
	public void addFood(int food) {
		this.food += food;
	}

	@Override
	public boolean isAccessibleTo(Human human) {
		return human.getHouse() == this;
	}

	@Override
	public void update() {}

	@Override
	public void step() {}

	public void add(Human human) {
		inhabitants.add(human);
	}
	
	public ArrayList<Human> getInhabitants() {
		return this.inhabitants;
	}
	
	public Human getPartner(Integer gender) {
		for (Human human: inhabitants)
			if (human.getGender() != gender && human.getAge() > 20)
				return human;
		return null;
	}
	
	public double remove(Human human) {
		int nbInhab = inhabitants.size();
		
		if (inhabitants.contains(human)) {
			inhabitants.remove(human);
			double result = money / nbInhab;
			this.money = (nbInhab - 1) * money;
			return result;
		}
		else {
			return 0.;
		}
	}
}