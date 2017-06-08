package context;

import agent.Agent;
import agent.Human;
import agent.place.Field;
import agent.place.House;
import agent.place.Place;
import agent.place.PlaceType;
import agent.place.Road;
import agent.place.School;
import cern.jet.random.Uniform;
import job.Banker;
import job.Driver;
import job.Farmer;
import job.Job;
import job.Seller;
import job.Teacher;
import repast.simphony.context.Context;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadMap {
	private String path;
	private int width;
	private int height;
	
	private float bankerProb;
	private float driverProb;
	private float farmerProb;
	private float sellerProb;
	private float teacherProb;
	
	private int nbMale = 0;
	private int nbFemale = 0;
	private int nbHouse = 0;
	
	private ArrayList<Human> people = new ArrayList<Human>();
	private ArrayList<Place> place = new ArrayList<Place>();
	
	public ReadMap() {
		this.path = "tests/carte.txt";

	}
	
	public Grid<Agent> createGrid(Context<Agent> context) {
        BufferedReader br = null;
        FileReader fr = null;
        Grid<Agent> grid = null;
        
        try {
            fr = new FileReader(this.path);
            br = new BufferedReader(fr);


            String sCurrentLine;

            br = new BufferedReader(new FileReader(this.path));
         
            sCurrentLine = br.readLine();
            this.width = Integer.parseInt(sCurrentLine.split(" ")[0]);
            this.height = Integer.parseInt(sCurrentLine.split(" ")[1]);
            System.out.println("height : " + height);
            System.out.println("width: " + width);
            int i = 0;
            int j = 0;

            
            GridFactory gfac = GridFactoryFinder.createGridFactory(null);
    		GridBuilderParameters<Agent> gbp = new GridBuilderParameters<Agent>(new WrapAroundBorders(), 
    				new SimpleGridAdder<Agent>(), false, this.width, this.height);
            grid = gfac.createGrid("grid", context, gbp);

            
            while ((sCurrentLine = br.readLine()) != null && i != width) {
                for (String mapElt : sCurrentLine.split(" ")) {
             //       System.out.println("mapElt: " + mapElt);
                	
                    switch (mapElt) {
                        case "F": { // Forest
                        	Place forest = new Place(i, j, grid, PlaceType.FOREST, 0, 0, 0);
                        	place.add(forest);
                        	context.add(forest);
                        	grid.moveTo(forest, i, j);
                        	break; 
                        }
                        case "f": { // Field 
                        	Field field = new Field(i, j, grid, 0, 0, 0);
                        	place.add(field);
                        	context.add(field);
                        	grid.moveTo(field, i, j);
                        	break;
                        }
                        case "W": { // Water
                        	Place water = new Place(i, j, grid, PlaceType.WATER, 0, 0, 0);
                        	place.add(water);
                        	context.add(water);
                        	grid.moveTo(water,  i, j);
                        	break; 
                        }
                        case "H": { // House 
                        	nbHouse++;
                        	House house = new House(i, j, grid, 0, 0, 0);
                        	place.add(house);
                        	context.add(house);
                        	grid.moveTo(house, i, j);
                        	break;
                        }
                        case "R": { // Road
                        	Place road = new Road(i, j, grid, 0, 0, 0);
                        	place.add(road);
                        	context.add(road);
                        	grid.moveTo(road,  i, j);
                        	break;
                        }
                        case "L": { // Land
                        	Place land = new Place(i, j, grid, PlaceType.LAND, 0, 0, 0);
                        	place.add(land);
                        	context.add(land);
                        	grid.moveTo(land,  i, j);
                        	break; }
                        case "C": { // Concrete
                        	Place concrete = new Place(i, j, grid, PlaceType.CONCRETE, 0, 0, 0);
                        	place.add(concrete);
                        	context.add(concrete);
                        	grid.moveTo(concrete,  i, j);
                        	break; }
                        case "S" : { // School
                        	Place school = new School(i, j, grid, 0, 0, 0);
                        	place.add(school);
                        	context.add(school);
                        	grid.moveTo(school,  i, j);
                        	break;
                        }
                        case "P" : { // Park
                        	Place park = new Place(i, j, grid, PlaceType.PARK, 0, 0, 0);
                        	place.add(park);
                        	context.add(park);
                        	grid.moveTo(park,  i, j);
                        	break;
                        }
                    }
                    j++;
                }
                System.out.println(sCurrentLine);
                i++;
            }
//            System.out.println("nb de House : " + nbHouse);


            for (int r = 0; r < 2; ++r) {
            	sCurrentLine = br.readLine(); 
            	String[] gender = sCurrentLine.split(" ");
            	switch (gender[0]) {
            		case "male"   : { this.nbMale = Integer.valueOf(gender[1]); break; }
            		case "female" : { this.nbFemale = Integer.valueOf(gender[1]); break; }
            	}
            }
                      
            while ((sCurrentLine = br.readLine()) != null) {
                String[] agentStat = sCurrentLine.split(" ");
                switch (agentStat[0]) {
            		case "BANKER"  : { this.bankerProb = Float.valueOf(agentStat[1]); break; }
            		case "DRIVER"  : { this.driverProb = Float.valueOf(agentStat[1]); break; }
            		case "FARMER"  : { this.farmerProb = Float.valueOf(agentStat[1]); break; }
            		case "SELLER"  : { this.sellerProb = Float.valueOf(agentStat[1]); break; }
            		case "TEACHER" : { this.teacherProb = Float.valueOf(agentStat[1]); break; }
                }
            }
          
            for (int k = 0; k < this.nbFemale; ++k) {
            	for (int n = 0; n < this.nbHouse; ++n) {
            		House currHouse = findHouse(n);
            		if (currHouse == null)
            			System.out.println("c'est la meeeeerde");
            		ArrayList<Human> tmplist = currHouse.getInhabitants(); 
            		if (tmplist.isEmpty()){
            			Human human = new Human(currHouse.getX(), currHouse.getY(), grid, 1, 20, selectJob());
            			people.add(human);
            			currHouse.add(human);
            			context.add(human);
            			grid.moveTo(human, currHouse.getX(), currHouse.getY());
            		}
            	}
            }

            for (int k = 0; k < this.nbMale; ++k) {
            	
            	for (int n = 0; n < this.nbHouse; ++n) {
         			House currHouse = findHouse(n);
         			ArrayList<Human> tmplist = currHouse.getInhabitants(); 
            		if (tmplist.size() == 1){
            			Human human = new Human(currHouse.getX(), currHouse.getY(), grid, 0, 20, selectJob());
                    	currHouse.add(human);
                    	people.add(human);
                    	context.add(human);
                    	grid.moveTo(human, currHouse.getX(), currHouse.getY());        		}
            	}
    
            }

            
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
		return grid;
    }
	
	
	public Job selectJob() {
		float sum = this.bankerProb;
		Uniform u = RandomHelper.createUniform();
		double x = u.nextDouble();
		
		if (x <= sum)
			return new Banker();
		else if (x <= (sum += this.driverProb))
			return new Driver();
		else if (x <= (sum += this.farmerProb))
			return new Farmer();
		else if (x <= (sum += this.sellerProb))
			return new Seller();
		else
			return new Teacher();
	}
	
	public House findHouse(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof House) {
				
				System.out.println("cest une house");
				if (position == 0) {
		//			System.out.println("c'est laaaaaa maisons");
					return (House) this.place.get(i);
				}
				position--;
				
			}

		}
		return null;
	}

	public School findSchool(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof School) {
				if (position == 0)
					return (School) this.place.get(i);
				position--;
			}

		}
		return null;
	}

	public Field findField(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof Field) {
				if (position == 0)
					return (Field) this.place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public Road findRoad(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof Road) {
				if (position == 0)
					return (Road) this.place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public Place findLand(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof Place && this.place.get(i).getType() == PlaceType.LAND) {
				if (position == 0)
					return (Place) this.place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public Place findConcrete(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof Place && this.place.get(i).getType() == PlaceType.CONCRETE) {
				if (position == 0)
					return (Place) this.place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public Place findWater(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof Place && this.place.get(i).getType() == PlaceType.WATER) {
				if (position == 0)
					return (Place) this.place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public Place findPark(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof Place && this.place.get(i).getType() == PlaceType.PARK) {
				if (position == 0)
					return (Place) this.place.get(i);
				position--;
			}

		}
		return null;
	}
	
	
	public Place findForest(int position) {
		for (int i = 0; i < this.place.size(); ++i) {
			if (this.place.get(i) instanceof Place && this.place.get(i).getType() == PlaceType.FOREST) {
				if (position == 0)
					return (Place) this.place.get(i);
				position--;
			}

		}
		return null;
	}
	
	
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	
	public float getBankerProb() {
		return this.bankerProb;
	}
	public float getDriverProb() {
		return this.driverProb;
	}
	public float getFarmerProb() {
		return this.farmerProb;
	}
	public float getSellerProb() {
		return this.sellerProb;
	}
	
	public float getTeacherProb() {
		return this.teacherProb;
	}
	
	
	public int getNbMale() {
		return this.nbMale;
	}

	public int getNbFemale() {
		return this.nbFemale;
	}
	
	
	public int getNbHouse() {
		return this.nbHouse;
	}
}
