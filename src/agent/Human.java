package agent;

import java.util.ArrayList;

import action.Action;
import action.GoToPlaceAction;
import agent.place.House;
import agent.place.PlaceType;
import job.Job;
import job.Student;
import repast.simphony.random.RandomHelper;
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

	public Human(int i, int j, Grid<Agent> grid, Human father, Human mother) {
		super(i, j, grid);
		
		this.gender = RandomHelper.nextIntFromTo(0, 1);
		this.maxAge = RandomHelper.createNormal(75, 10).nextInt();
		this.age 	= 0;
		
		this.mood 		= 50;
		this.energy 	= 50;
		this.hunger 	= 50;
		this.education 	= 0;
		
		this.parents 	= new ArrayList<>();
		this.siblings 	= new ArrayList<>();
		this.children 	= new ArrayList<>();
		
		this.parents.add(father);
		this.parents.add(mother);
		this.siblings.addAll(father.children); // TODO: le father instancie l'enfant puis l'ajoute lors de la reproduction à ses children.
		
		this.currentAction 	= null;
		this.job 			= new Student();
		father.house.add(this);
	}
	
	public Human(int i, int j, Grid<Agent> grid, int gender, int age, Job job) {
		super(i, j, grid);
		this.gender = gender;
		this.maxAge = RandomHelper.createNormal(75, 10).nextInt();
		this.age 	= age;

		this.mood 		= 50;
		this.energy 	= 50;
		this.hunger 	= 50;
		this.education 	= 50;

		this.parents 	= new ArrayList<>();
		this.siblings 	= new ArrayList<>();
		this.children 	= new ArrayList<>();

		this.currentAction 	= null;
		this.job 			= job;
		this.house 			= null;
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
	public void addHouse(House house) {
		this.house = house;
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
	public ArrayList<Human> getParents() {
		return this.parents;
	}
	public ArrayList<Human> getSiblings() {
		return this.siblings;
	}
	public ArrayList<Human> getChildren() {
		return this.children;
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
		// TODO: Interagir avec le terrain pour mettre ï¿½ jour mood, energy, hunger, education
		
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
			// DEBUG:
			currentAction = new GoToPlaceAction(this, PlaceType.FIELD);
			// TODO: Reproduire (Si partenaire ï¿½ la maison, maisons disponibles pour le sexe, assez vieux), ou combler le besoin au minimum (mood, energy, food, money)
			currentAction.initiate();
		}
		currentAction.step();
	}
	
	public Action goGetMood() {
		// TODO: si dans un parc, WaitAction, sinon GoToPlaceAction => Parc
		return null;
	}
	
	public Action goGetSleep() {
		// TODO: si ï¿½ la maison, SleepAction, sinon GoToPlaceAction => House
		return null;
	}
	
	public Action goGetFood() {
		// TODO: si auprï¿½s d'un Seller => BuyFood, sinon GoToHumanAction => Seller
		return null;
	}
	
	public Action goGetMoney() {
		return job.getNextStep(this);
	}
	
	public Action goGetAdulthood() {
		int budget = house.remove(this);
		// TODO: remove de la House avec une part de l'argent, transfert dans une autre maison disponible, acquiert un nouveau job
		return null;
	}
}