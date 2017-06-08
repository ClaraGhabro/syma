package agent;

import java.util.ArrayList;

import action.Action;
import action.BuyFoodAction;
import action.GoToHumanAction;
import action.GoToPlaceAction;
import action.SleepAction;
import action.WaitAction;
import agent.place.House;
import agent.place.Place;
import agent.place.PlaceType;
import context.Constants;
import context.ContextCreator;
import context.ReadMap;
import job.Job;
import job.JobType;
import job.Student;
import repast.simphony.engine.environment.RunEnvironment;
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
	private int foodQuantity;
	private String name;

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
		
		this.mood 		= Constants.average;
		this.energy 	= Constants.average;
		this.hunger 	= Constants.average;
		this.education 	= 0;
		this.foodQuantity = 0;
		
		this.parents 	= new ArrayList<>();
		this.siblings 	= new ArrayList<>();
		this.children 	= new ArrayList<>();
		
		this.parents.add(father);
		this.parents.add(mother);
		this.siblings.addAll(father.children);
		
		this.currentAction 	= null;
		this.job 			= new Student();
		father.house.add(this);
		this.name = ContextCreator.getRandomName();
	}
	
	public Human(int i, int j, Grid<Agent> grid, int gender, int age, Job job) {
		super(i, j, grid);
		this.gender = gender;
		this.maxAge = RandomHelper.createNormal(75, 10).nextInt();
		this.age 	= age;
		
		this.mood 		= Constants.average;
		this.energy 	= Constants.average;
		this.hunger 	= Constants.average;
		this.education 	= Constants.average;
		this.foodQuantity = 0;


		this.parents 	= new ArrayList<>();
		this.siblings 	= new ArrayList<>();
		this.children 	= new ArrayList<>();

		this.currentAction 	= null;
		this.job 			= job;
		this.house 			= null;
		this.name = ContextCreator.getRandomName();
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
	public int getGender() {
		return gender;
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
	public String getName() {
		return this.name;
	}
	public int getAge() {
		return this.age;
	}
	public void resetAction() {
		this.currentAction = null;
	}
	public double getPrice() {
		return education / 10 + 0.1;
	}
	public int getFoodQuantity() {
		return foodQuantity;
	}
	public void addQuantity(int quantity) {
		foodQuantity += quantity;
	}


	@Override
	public void update() {
		Place currentPlace = ContextCreator.getPlaceAt(x, y);
		this.addMood(currentPlace.getMood());
		this.addEnergy(currentPlace.getEnergy());
		this.addHunger(currentPlace.getHunger());

		if (RunEnvironment.getInstance().getCurrentSchedule().getTickCount() % 52560 == 0)
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
			return;
		}

		if (currentAction == null) {
			// BEG: DEBUG
			currentAction = new GoToPlaceAction(this, PlaceType.FIELD);
			currentAction.initiate();
			if (currentAction.getDuration() == 0)
				currentAction = new WaitAction(this);
			// END: DEBUG
			// TODO: Reproduire (Si partenaire a la maison, maisons disponibles pour le sexe, assez vieux),
			// ou combler le besoin au minimum (mood, energy, food, money)
		}
		currentAction.step();
	}
	
	public Action goGetMood() {
		Place place = ContextCreator.getPlaceAt(this.x, this.y);
		if (place.getType() == PlaceType.PARK) {
			WaitAction wait = new WaitAction(this);
			return wait;
		}
		return new GoToPlaceAction(this, PlaceType.PARK);
	}
	
	public Action goGetSleep() {
		Place place = ContextCreator.getPlaceAt(this.x, this.y);
		if (place.getType() == PlaceType.HOUSE) {
			SleepAction sleep = new SleepAction(this);
			return sleep;
		}
		GoToPlaceAction goPlace = new GoToPlaceAction(this, PlaceType.HOUSE);
		return goPlace;
	}
	
	public Action goGetFood() {
		// if Human is a Seller, don't need to buy, just eat
		if (this.job.getJobType() == JobType.SELLER) {
			BuyFoodAction buy = new BuyFoodAction(this, this);
			return buy;
		}
		
		ArrayList<Human> humans = ContextCreator.getHumansWithJobAt(this.x, this.y, JobType.SELLER);
		for (Human someone: humans) {
			if (someone.x == this.x && someone.y == this.y){
				BuyFoodAction buy = new BuyFoodAction(this, someone);
				return buy;
			}
		}
		
		GoToHumanAction goHuman = new GoToHumanAction(this, JobType.SELLER);
		return goHuman;
	}
	
	public Action goGetMoney() {
		return job.getNextStep(this);
	}
	
	public Action goGetAdulthood() {
		double budget = house.remove(this);
		for (int i = 0; i < ReadMap.nbHouse; ++i) {
			House newHouse = ReadMap.findHouse(i);
			ArrayList<Human> inhabitant = newHouse.getInhabitants(); 
			
			if (newHouse.getInhabitants().isEmpty() 
					|| inhabitant.size() == 1 && inhabitant.get(0).gender != this.gender) {
				newHouse.add(this);
				this.house = newHouse;
				newHouse.addMoney(budget);
				WaitAction wait = new WaitAction(this);
				return wait;
			}
		}
		// TODO: remove de la House avec une part de l'argent, transfert dans une autre maison disponible, acquiert un nouveau job
		WaitAction wait = new WaitAction(this);
		return wait;
	}
}