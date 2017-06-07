package agent;

import java.util.ArrayList;

import action.Action;
import agent.place.House;
import job.Job;
import repast.simphony.space.grid.Grid;

public class Human extends Agent{
	private int gender; // 0 : male, 1 : female
	private int maxAge;
	private int age;

	private int mood;
	private int energy;
	private int hunger;
	private int education;

	private ArrayList<Human> parents;
	private ArrayList<Human> siblings;
	private ArrayList<Human> children;

	private Action currentAction;
	private Job job;
	private House house;

	public Human(int i, int j, Grid<Agent> grid) {
		super(i, j, grid);
	}
	
	public Human(int i, int j, Grid<Agent> grid, int gender, int age, Job job) {
		super(i, j, grid);
		this.gender = gender;
		this.age = age;
		this.job = job;
	}

	public void addMood(int mood) {
		this.mood += mood;
	}
	public void addEnergy(int energy) {
		this.energy += energy;
	}
	public void addHunger(int hunger) {
		this.hunger += hunger;
	}
	public void addEducation(int education) {
		this.education += education;
	}
	public int getMood() {
		return mood;
	}
	public int getEnergy() {
		return energy;
	}
	public int getHunger() {
		return hunger;
	}
	public int getEducation() {
		return education;
	}
	public Job getJob() {
		return this.job;
	}
	public House getHouse() {
		return this.house;
	}

	@Override
	public void update() {
		// TODO: Interagir avec le terrain pour mettre � jour mood, energy, hunger, education
		
		age++;		
		currentAction.update();
		
		if (currentAction.getDuration() == 0)
			this.currentAction = null;
	}

	@Override
	public void step() {
		if (mood <= 0 || energy <= 0 || hunger <= 0 || age > maxAge) {
			die();
			house.remove(this);
		}
		
		if (currentAction == null) {
			// TODO: Reproduire (Si partenaire � la maison, maisons disponibles pour le sexe, assez vieux), ou combler le besoin au minimum (mood, energy, food, money)
			currentAction.initiate();
		}
		currentAction.step();
	}
	
	public Action goGetMood() {
		// TODO: si dans un parc, WaitAction, sinon GoToPlaceAction => Parc
		return null;
	}
	
	public Action goGetSleep() {
		// TODO: si � la maison, SleepAction, sinon GoToPlaceAction => House
		return null;
	}
	
	public Action goGetFood() {
		// TODO: si aupr�s d'un Seller => BuyFood, sinon GoToHumanAction => Seller
		return null;
	}
	
	public Action goGetMoney() {
		return job.getNextStep();
	}
	
	public Action goGetAdulthood() {
		int budget = house.remove(this);
		// TODO: remove de la House avec une part de l'argent, transfert dans une autre maison disponible, acquiert un nouveau job
		return null;
	}
}