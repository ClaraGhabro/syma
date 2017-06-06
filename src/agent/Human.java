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

	private Action current_action;
	private Job job;
	private House house;

	public Human(int i, int j, Grid<Agent> grid) {
		super(i, j, grid);
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
	public Job getJob() {
		return this.job;
	}
	public House getHouse() {
		return this.house;
	}

	@Override
	public void update() {
		// TODO: Interagir avec le terrain pour mettre à jour mood, energy, hunger, education
		// TODO: Mettre à jour l'action, l'âge
	}

	@Override
	public void step() {
		// TODO: Choisir ou continuer une action
	}
}