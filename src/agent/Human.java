package agent;

import java.util.ArrayList;

import action.Action;
import action.BuyFoodAction;
import action.EatAction;
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
import repast.simphony.context.Context;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.util.ContextUtils;

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
	private String action;

	private ArrayList<Human> parents;
	private ArrayList<Human> siblings;
	private ArrayList<Human> children;

	private Action currentAction;
	private Job job;
	private House house;
	
	public Human(Human other) {
		super(other.getX(), other.getY(), other.getGrid());
		
		this.gender = other.gender;
		this.maxAge = other.maxAge;
		this.age 	= other.age;
		this.reproduceCount = other.reproduceCount;
		
		this.mood 		= other.mood;
		this.energy 	= other.energy;
		this.hunger 	= other.hunger;
		this.education 	= other.education;
		
		this.parents 	= other.parents;
		this.siblings 	= other.siblings;
		this.children 	= other.children;
		
		this.job 			= ReadMap.selectJob();
		this.name = other.name;
		this.currentAction = new WaitAction(this, 1);
	}

	public Human(int i, int j, Grid<Agent> grid, Human father, Human mother) {
		super(i, j, grid);
		
		this.gender = RandomHelper.nextIntFromTo(0, 1);
		this.maxAge = RandomHelper.createNormal(75, 10).nextInt();
		this.age 	= 0;
		this.reproduceCount = 0;
		
		this.mood 		= Constants.average;
		this.energy 	= Constants.average;
		this.hunger 	= Constants.average;
		this.education 	= 10;
		
		this.parents 	= new ArrayList<>();
		this.siblings 	= new ArrayList<>();
		this.children 	= new ArrayList<>();
		
		this.parents.add(father);
		this.parents.add(mother);
		this.siblings.addAll(father.children);
		
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
		this.education 	= 10;


		this.parents 	= new ArrayList<>();
		this.siblings 	= new ArrayList<>();
		this.children 	= new ArrayList<>();

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
	public String getJobName() {
		return this.job.getJobType().toString();
	}
	public String getParentsNames() {
		if (parents.size() == 0)
			return "No parents";
		return this.parents.get(0).name + " and " + this.parents.get(1).name;
	}
	public int getNbSiblings() {
		return this.siblings.size();
	}
	public House getHouse() {
		return this.house;
	}
	public String getName() {
		return this.name;
	}
	public String getAction() {
		return this.action;
	}
	public int getAge() {
		return this.age;
	}
	public double getMoney() {
		return this.house.getMoney();
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
		
		if (currentAction.getDuration() <= 0)
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
			if (house.getInhabitants().size() == 0)
			{
				currentAction = new WaitAction(this, 1);
				return;
			}
			int minNeed = Math.min(mood - 20, Math.min(energy - 20, Math.min(hunger - 20, Math.min(house.getFood() - 10, (int) house.getMoney() / house.getInhabitants().size()))));
			int minNeedReproduce = Math.min(mood, Math.min(energy, hunger));
			if (age >= 25 && age < 45
					&& reproduceCount <= 0 && minNeedReproduce > 30
					&& house.getPartner(gender) != null
					&& ReadMap.findEmptyHouses(gender).size() != 0
					&& gender == 1) {
				currentAction = new ReproduceAction(this, house.getPartner(gender));
			}
			else if (mood - 20 <= minNeed) {
				currentAction = goGetMood();
			}
			else if (energy - 20 <= minNeed) {
				currentAction = goGetSleep();
			}
			else if (hunger - 20 <= minNeed || house.getFood() - 10 <= minNeed) {
				currentAction = goGetFood();
			}
			else if ((int) house.getMoney() / house.getInhabitants().size() <= minNeed) {
				currentAction = job.getNextStep(this);
			}
			
			currentAction.initiate();
		}
		action = this.name + " " + currentAction;
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
		if ((house.getFood() < Constants.maximumFood && house.getFood() > Constants.minimumFood) || (hunger < Constants.minimumHunger && house.getFood() > 0)) {
			return new EatAction(this);
		}
		if (job.getJobType() == JobType.FARMER)
			return job.getNextStep(this);
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
		int food = house.getFood() / (house.getInhabitants().size() + 1);
		house.addFood(-food);
		for (int i = 0; i < ReadMap.nbHouse; ++i) {
			House newHouse = ReadMap.findHouse(i);
			ArrayList<Human> inhabitant = newHouse.getInhabitants();
			if (newHouse.getInhabitants().isEmpty() 
					|| (inhabitant.size() == 1 && inhabitant.get(0).gender != this.gender)) {
				Human adult = new Human(this);
				adult.addHouse(newHouse);
				newHouse.add(adult);
				newHouse.addMoney(budget);
				newHouse.addFood(food);
				Context<Agent> context = ContextUtils.getContext(this);
				context.add(adult);
				this.grid.moveTo(adult, this.x, this.y);
				context.remove(this);
				return new WaitAction(this, 1);
			}
		}
		return new WaitAction(this, 1);
	}
}