package agent.place;

import java.util.ArrayList;

import agent.Agent;
import agent.Human;
import repast.simphony.space.grid.Grid;

public class House extends Place {
	private double money;
	private int food;
	private int nbInhabitants;
	private ArrayList<Human> inhabitants;

	public House(int i, int j, Grid<Agent> grid, int mood, int energy, int hunger) {
		super(i, j, grid, PlaceType.HOUSE, mood, energy, hunger);
		this.inhabitants = new ArrayList<>();
		this.setNbInhabitants(0);
		this.money = 50;
		this.food = 50;
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
	
	public int getNbInhabitants() {
		return nbInhabitants;
	}

	public void setNbInhabitants(int nbInhabitants) {
		this.nbInhabitants = nbInhabitants;
	}

	@Override
	public boolean isAccessibleTo(Human human) {
		return human.getHouse() == this;
	}

	@Override
	public void update() {
		this.nbInhabitants = inhabitants.size();
	}

	@Override
	public void step() {}

	public void add(Human human) {
		inhabitants.add(human);
		this.setNbInhabitants(this.getNbInhabitants() + 1);
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
			this.setNbInhabitants(this.getNbInhabitants() - 1);
			double result = money / nbInhab;
			this.money = (nbInhab - 1) * result;
			return result;
		}
		else {
			return 0.;
		}
	}
}
