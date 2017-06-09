package context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

public class ReadMap {
	private String path;
	private int width;
	private int height;
	
	private static float bankerProb = 0;
	private static float driverProb = 0;
	private static float farmerProb = 0;
	private static float sellerProb = 0;
	private static float teacherProb = 0;
	
	private int nbMale = 0;
	private int nbFemale = 0;
	public static int nbHouse = 0;
	
	private static ArrayList<Human> people = new ArrayList<Human>();
	public static ArrayList<Place> place = new ArrayList<Place>();
	
	public ReadMap() {
		this.path = "tests/map.txt";
		//this.path = "tests/map_test.txt";
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
            this.width = Integer.parseInt(sCurrentLine.split(" ")[1]);
            this.height = Integer.parseInt(sCurrentLine.split(" ")[0]);
            System.out.println("height : " + height);
            System.out.println("width: " + width);
            int i = -1;
            int j = 0;

            
            GridFactory gfac = GridFactoryFinder.createGridFactory(null);
    		GridBuilderParameters<Agent> gbp = new GridBuilderParameters<Agent>(new WrapAroundBorders(), 
    				new SimpleGridAdder<Agent>(), true, this.width, this.height);
            grid = gfac.createGrid("grid", context, gbp);

            
            while ((sCurrentLine = br.readLine()) != null && i < this.height) {
            	j = 0;
            //	System.out.println(sCurrentLine);
                for (String mapElt : sCurrentLine.split(" ")) {
                	// System.out.println("mapElt: " + mapElt);
                	
                 	
                    switch (mapElt) {
                        case "F": { // Forest
                        	Place forest = new Place(j, i, grid, PlaceType.FOREST, 3, 7, 3);
                        	place.add(forest);
                        	context.add(forest);
                        	grid.moveTo(forest, j, i);
                        	break; 
                        }
                        case "f": { // Field 
                        	Field field = new Field(j, i, grid, 3, 5, 5);
                        	place.add(field);
                        	context.add(field);
                        	grid.moveTo(field, j, i);
                        	break;
                        }
                        case "W": { // Water
                        	Place water = new Place(j, i, grid, PlaceType.WATER, 10, 15, 15);
                        	place.add(water);
                        	context.add(water);
                        	grid.moveTo(water,  j, i);
                        	break; 
                        }
                        case "H": { // House 
                        	nbHouse++;
                        	House house = new House(j, i, grid, 2, 2, 2);
                        	place.add(house);
                        	context.add(house);
                        	grid.moveTo(house, j, i);
                        	break;
                        }
                        case "R": { // Road
                        	Place road = new Road(j, i, grid, 2, 2, 2);
                        	place.add(road);
                        	context.add(road);
                        	grid.moveTo(road,  j, i);
                        	break;
                        }
                        case "L": { // Land
                        	Place land = new Place(j, i, grid, PlaceType.LAND, 3, 3, 3);
                        	place.add(land);
                        	context.add(land);
                        	grid.moveTo(land,  j, i);
                        	break; }
                        case "C": { // Concrete
                        	Place concrete = new Place(j, i, grid, PlaceType.CONCRETE, 3, 2, 2);
                        	place.add(concrete);
                        	context.add(concrete);
                        	grid.moveTo(concrete,  j, i);
                        	break; }
                        case "S" : { // School
                        	Place school = new School(j, i, grid, 1, 1, 1);
                        	place.add(school);
                        	context.add(school);
                        	grid.moveTo(school,  j, i);
                        	break;
                        }
                        case "P" : { // Park
                        	Place park = new Place(j, i, grid, PlaceType.PARK, 1, 1, 1);
                        	place.add(park);
                        	context.add(park);
                        	grid.moveTo(park,  j, i);
                        	break;
                        }
                    }
                    j++;
                }
                i++;
            }
            System.out.println("nb de House : " + nbHouse);


            for (int r = 0; r < 2; ++r) {
            	sCurrentLine = br.readLine(); 
            	System.out.println("br: " + sCurrentLine);
            	String[] gender = sCurrentLine.split(" ");
            	switch (gender[0]) {
            		case "male"   : { this.nbMale = Integer.valueOf(gender[1]); break; }
            		case "female" : { this.nbFemale = Integer.valueOf(gender[1]); break; }
            	}
            }
            System.out.println("nb homme: " + this.nbMale);
            System.out.println("nb femme: " + this.nbFemale);
                      
            while ((sCurrentLine = br.readLine()) != null) {

        //    	System.out.println("br: " + sCurrentLine);
                String[] agentStat = sCurrentLine.split(" ");
                switch (agentStat[0]) {
            		case "BANKER"  : { this.bankerProb = Float.valueOf(agentStat[1]); break; }
       //     		case "DRIVER"  : { this.driverProb = Float.valueOf(agentStat[1]); break; }
            		case "FARMER"  : { this.farmerProb = Float.valueOf(agentStat[1]); break; }
            		case "SELLER"  : { this.sellerProb = Float.valueOf(agentStat[1]); break; }
            		case "TEACHER" : { this.teacherProb = Float.valueOf(agentStat[1]); break; }
                }
            }
          
            for (int k = 0; k < this.nbFemale; ++k) {
            	for (int n = 0; n < this.nbHouse; ++n) {
            		House currHouse = findHouse(n);
            		if (currHouse == null)
            			System.out.println("Plus d'habitation disponible.");
            		ArrayList<Human> tmplist = currHouse.getInhabitants();
            		if (tmplist.isEmpty()){
            			Human human = new Human(currHouse.getX(), currHouse.getY(), grid, 1, 20, selectJob());
            			people.add(human);
            			currHouse.add(human);
                    	human.addHouse(currHouse);
            			context.add(human);
            			grid.moveTo(human, currHouse.getX(), currHouse.getY());
            			break;
            		}
            	}
            }

            for (int k = 0; k < this.nbMale; ++k) {
            	for (int n = 0; n < this.nbHouse; ++n) {
         			House currHouse = findHouse(n);
            		if (currHouse == null)
            			System.out.println("Plus d'habitation disponible.");
         			ArrayList<Human> tmplist = currHouse.getInhabitants(); 
            		if (tmplist.size() == 1 && tmplist.get(0).getGender() == 1){
            			Human human = new Human(currHouse.getX(), currHouse.getY(), grid, 0, 20, selectJob());
                    	currHouse.add(human);
                    	human.addHouse(currHouse);
                    	people.add(human);
                    	context.add(human);
                    	grid.moveTo(human, currHouse.getX(), currHouse.getY());
                    	break;
                    }
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
	
	static public Job selectJob() {
		float sum = bankerProb;
		Uniform u = RandomHelper.createUniform();
		double x = u.nextDouble();
		
		if (x <= sum)
			return new Banker();
		else if (x <= (sum += driverProb))
			return new Driver();
		else if (x <= (sum += farmerProb))
			return new Farmer();
		else if (x <= (sum += sellerProb))
			return new Seller();
		else
			return new Teacher();
	}
	
	public static ArrayList<House> findEmptyHouses(Integer gender) {
		ArrayList<House> result = new ArrayList<>();
		for (Place p: place) {
			if (p instanceof House) {
				boolean empty = true;
				for (Human h: ((House) p).getInhabitants())
					if (h.getGender() == gender)
						empty = false;
				if (empty)
					result.add((House) p);
			}
		}
		return result;
	}
	
	public static House findHouse(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof House) {
				if (position == 0) {
					return (House) place.get(i);
				}
				position--;				
			}
		}
		return null;
	}

	public static School findSchool(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof School) {
				if (position == 0)
					return (School) place.get(i);
				position--;
			}

		}
		return null;
	}

	public static Field findField(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof Field) {
				if (position == 0)
					return (Field) place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public static Road findRoad(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof Road) {
				if (position == 0)
					return (Road) place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public static Place findLand(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof Place && place.get(i).getType() == PlaceType.LAND) {
				if (position == 0)
					return (Place) place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public static Place findConcrete(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof Place && place.get(i).getType() == PlaceType.CONCRETE) {
				if (position == 0)
					return (Place) place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public static Place findWater(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof Place && place.get(i).getType() == PlaceType.WATER) {
				if (position == 0)
					return (Place) place.get(i);
				position--;
			}

		}
		return null;
	}
	
	public static Place findPark(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof Place && place.get(i).getType() == PlaceType.PARK) {
				if (position == 0)
					return (Place) place.get(i);
				position--;
			}

		}
		return null;
	}
	
	
	public static Place findForest(int position) {
		for (int i = 0; i < place.size(); ++i) {
			if (place.get(i) instanceof Place && place.get(i).getType() == PlaceType.FOREST) {
				if (position == 0)
					return (Place) place.get(i);
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
