package syma;

import repast.simphony.space.grid.Grid;

public class Human extends Agent{
	private int gender; // 0 : male, 1 : female
	private int max_age;
	private int age;
	private int faim;
	private int energie;
	private Action current_action;
	
	public Human(int i, int j, Grid<Agent> grid2, int gender) {
		super(i, j, grid2);
		this.gender = gender;
	}


	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}

}
