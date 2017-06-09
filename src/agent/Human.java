package agent;

import java.util.ArrayList;

import action.Action;
import action.BuyFoodAction;
import action.GoToHumanAction;
import action.GoToPlaceAction;
import action.RelaxAction;
import action.ReproduceAction;
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
	private int reproduceCount;

	private int mood;
	private int energy;
	private int hunger;
	private int education;
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
		this.reproduceCount = 0;
		
		this.mood 		= Constants.average;
		this.energy 	= Constants.average;
		this.hunger 	= Constants.average;
		this.education 	= 5;
		
		this.parents 	= new ArrayList<>();
		this.siblings 	= new ArrayList<>();
		this.children 	= new ArrayList<>();
		
		this.parents.add(father);
		this.parents.add(mother);
		this.siblings.addAll(father.children);
		
		this.currentAction 	= null;
		this.job 			= new Student();
		father.house.add(this);
		this.name = ContextCreator.getRandomName(gender);
		this.currentAction = new WaitAction(this, 1);
	}
	
	public Human(int i, int j, Grid<Agent> grid, int gender, int age, Job job) {
		super(i, j, grid);
		this.gender = gender;
		this.maxAge = RandomHelper.createNormal(75, 10).nextInt();
		this.age 	= age;
		this.reproduceCount = 0;
		
		this.mood 		= Constants.average;
		this.energy 	= Constants.average;
		this.hunger 	= Constants.average;
		this.education 	= 5;


		this.parents 	= new ArrayList<>();
		this.siblings 	= new ArrayList<>();
		this.children 	= new ArrayList<>();

		this.currentAction 	= null;
		this.job 			= job;
		this.house 			= null;
		this.name = ContextCreator.getRandomName(gender);
		this.currentAction = new WaitAction(this, 1);
	}

	public void setReproduceCount(int count) {
		this.reproduceCount = count;
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

	@Override
	public void update() {
		Place currentPlace = ContextCreator.getPlaceAt(x, y);
		if (RunEnvironment.getInstance().getCurrentSchedule().getTickCount() % Constants.updateTick == 0)
			currentPlace.affect(this);

		if (RunEnvironment.getInstance().getCurrentSchedule().getTickCount() % Constants.yearCount == 0) {
			age++;
			reproduceCount--;
		}
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
			int minNeed = Math.min(mood - 20, Math.min(energy - 20, Math.min(hunger - 20, (int) house.getMoney() / house.getInhabitants().size())));
			int minNeedReproduce = Math.min(mood, Math.min(energy, hunger));
			System.out.println("Reproduce ? " + minNeedReproduce);
			if (age >= 25 && age < 35
					&& reproduceCount <= 0 && minNeedReproduce > 40
					&& house.getPartner(gender) != null
					&& ReadMap.findEmptyHouses(gender).size() != 0
					&& gender == 1) {
				System.out.println("SEX");
				currentAction = new ReproduceAction(this, house.getPartner(gender));
			}
			else if (mood - 20 <= minNeed) {
				System.out.println("MOOD");
				currentAction = goGetMood();
			}
			else if (energy - 20 <= minNeed) {
				System.out.println("SLEEP");
				currentAction = goGetSleep();
			}
			else if (hunger - 20 <= minNeed) {
				System.out.println("FOOD");
				currentAction = goGetFood();
			}
			else if ((int) house.getMoney() / house.getInhabitants().size() <= minNeed) {
				System.out.println("WORK");
				currentAction = job.getNextStep(this);
			}
			
			currentAction.initiate();
		}
		System.out.println(currentAction);
		System.out.println("name: " + this.name + " age: " + this.age + " education: " + this.education + " energy: " + this.energy + " hunger: " + this.hunger + " mood: " + this.mood);
		currentAction.step();
	}
	
	public Action goGetMood() {
		Place place = ContextCreator.getPlaceAt(this.x, this.y);
		if (place.getType() == PlaceType.PARK) {
			RelaxAction wait = new RelaxAction(this);
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
				return new WaitAction(this, 1);
			}
		}
		WaitAction wait = new WaitAction(this, 1);
		return wait;
	}
}