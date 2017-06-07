package context;

import agent.Agent;
import agent.Human;
import agent.place.Field;
import agent.place.House;
import agent.place.Place;
import agent.place.PlaceType;
import agent.place.School;
import cern.jet.random.Uniform;
import repast.simphony.context.Context;
import repast.simphony.context.space.grid.GridFactory;
import repast.simphony.context.space.grid.GridFactoryFinder;
import repast.simphony.random.RandomHelper;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridBuilderParameters;
import repast.simphony.space.grid.SimpleGridAdder;
import repast.simphony.space.grid.WrapAroundBorders;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMap {
	private String path;
	private int width;
	private int height;
	
	private float bankerProb;
	private float driverProb;
	private float farmerProb;
	private float sellerProb;
	private float teacherProb;
	
	
	
	public ReadMap() {
		this.path = "tests/carte.txt";

	}
	
	public Grid<Agent> createGrid(Context<Agent> context) {
        BufferedReader br = null;
        FileReader fr = null;
        
        GridFactory gfac = GridFactoryFinder.createGridFactory(null);
		GridBuilderParameters<Agent> gbp = new GridBuilderParameters<Agent>(new WrapAroundBorders(), 
				new SimpleGridAdder<Agent>(), true, this.width, this.height);
        Grid<Agent> grid = gfac.createGrid("grid", context, gbp);

        /*
        Agent aa = new ResourceCreator(0, 0, grid, Color.BLACK);
		context.add(aa);
		grid.moveTo(aa, i, j);
        */
        
        try {
            fr = new FileReader(this.path);
            br = new BufferedReader(fr);

            String sCurrentLine;

            br = new BufferedReader(new FileReader(this.path));
            int F = 0;
            int f = 0;
            int W = 0;
            int H = 0;
            int R = 0;
            int L = 0;
            int C = 0;
            int S = 0;
            int P = 0;

            sCurrentLine = br.readLine();
            this.width = Integer.parseInt(sCurrentLine.split(" ")[0]);
            this.height = Integer.parseInt(sCurrentLine.split(" ")[1]);

            int i = 0;
            int j = 0;

            while ((sCurrentLine = br.readLine()) != null && i != width) {
                for (String mapElt : sCurrentLine.split(" ")) {
                    switch (mapElt) {
                        case "F": { // Forest
                        	F++; 
                        	Place forest = new Place(i, j, grid, PlaceType.FOREST, 0, 0, 0);
                        	context.add(forest);
                        	grid.moveTo(forest, i, j);
                        	break; 
                        }
                        case "f": { // Field 
                        	f++;
                        	Field field = new Field(i, j, grid, 0, 0, 0);
                        	context.add(field);
                        	grid.moveTo(field, i, j);
                        	break;
                        }
                        case "W": { // Water
                        	W++; 
                        	Place water = new Place(i, j, grid, PlaceType.WATER, 0, 0, 0);
                        	context.add(water);
                        	grid.moveTo(water,  i, j);
                        	break; 
                        }
                        case "H": { // House 
                        	H++; 
                        	House house = new House(i, j, grid, 0, 0, 0);
                        	context.add(house);
                        	grid.moveTo(house, i, j);
                        	break;
                        }
                        case "R": { // Road
                        	R++;
                        	Place raod = new Place(i, j, grid, PlaceType.ROAD, 0, 0, 0);
                        	context.add(road);
                        	grid.moveTo(road,  i, j);
                        	break;
                        }
                        case "L": { // Land
                        	L++; 
                        	Place land = new Place(i, j, grid, PlaceType.LAND, 0, 0, 0);
                        	context.add(land);
                        	grid.moveTo(land,  i, j);
                        	break; }
                        case "C": { // Concrete
                        	C++;
                        	Place concrete = new Place(i, j, grid, PlaceType.CONCRETE, 0, 0, 0);
                        	context.add(concrete);
                        	grid.moveTo(concrete,  i, j);
                        	break; }
                        case "S" : {
                        	S++;
                        	Place school = new School(i, j, grid, 0, 0, 0);
                        	context.add(school);
                        	grid.moveTo(school,  i, j);
                        	break;
                        }
                        case "P" : {
                        	P++;
                        	Place park = new Place(i, j, grid, PlaceType.PARK, 0, 0, 0);
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
//            System.out.println("nb de F : " + F);
//            System.out.println("nb de f : " + f);
//            System.out.println("nb de W : " + W);
//            System.out.println("nb de H : " + H);
//            System.out.println("nb de R : " + R);
//            System.out.println("nb de L : " + L);
//            System.out.println("nb de C : " + C);

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
	


}
