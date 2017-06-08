package graphic;

import java.io.IOException;

import agent.Human;
import agent.place.Field;
import agent.place.House;
import agent.place.Place;
import agent.place.PlaceType;
import agent.place.School;
import context.ContextCreator;
import job.JobType;

import agent.place.Road;
import repast.simphony.visualizationOGL2D.DefaultStyleOGL2D;
import saf.v3d.scene.VSpatial;

public class Tiles extends DefaultStyleOGL2D {
//	String path; // 16 - 25 pixels 
	String road      = "icons/place/road.png";
	String forest    = "icons/place/forest.png";
	String water     = "icons/place/wave.png";
	String land      = "icons/place/land.png";
	String y_field   = "icons/place/y_field.png";
	String o_field   = "icons/place/o_field.png";
	String concrete  = "icons/place/concrete.png";
	String house     = "icons/place/house.png";
	String school    = "icons/place/school.png";
	String park      = "icons/place/park.png";
	
	String h_car     = "icons/job/h_car.png";
	String v_car     = "icons/job/v_car.png";
	String banker    = "icons/job/banker.png";
	String farmer    = "icons/job/farmer.png";
	String driver    = "icons/job/driver.png";
	String seller    = "icons/job/seller.png";
	String student_m = "icons/job/student_male.png";
	String student_f = "icons/job/student_female.png";
	String teacher   = "icons/job/teacher.png";
	
	// donner le path de tous les chemins, les associer a chaque objet avec le instanceof
	
	@Override
	public VSpatial getVSpatial(Object agent, VSpatial spatial) {

		if (spatial == null) {
			try {
				if (agent instanceof Road){
					spatial = shapeFactory.createImage(this.road);
				}
				else if (agent instanceof House) {
					spatial = shapeFactory.createImage(house);
				}
				else if (agent instanceof School) {
					spatial = shapeFactory.createImage(school);
				}
				else if (agent instanceof Field) {
					Field f = (Field) agent;
					if (f.getAge() < f.getMinAge())
						spatial = shapeFactory.createImage(y_field);
					else
						spatial = shapeFactory.createImage(o_field);
				}
				else if (agent instanceof Place && ((Place) agent).getType() == PlaceType.WATER) {
					spatial = shapeFactory.createImage(water);
				}
				else if (agent instanceof Place && ((Place) agent).getType() == PlaceType.FOREST) {
					spatial = shapeFactory.createImage(forest);
				}
				else if (agent instanceof Place && ((Place) agent).getType() == PlaceType.LAND) {
					spatial = shapeFactory.createImage(land);
				}
				else if (agent instanceof Place && ((Place) agent).getType() == PlaceType.CONCRETE) {
					spatial = shapeFactory.createImage(concrete);
				}
				else if (agent instanceof Place && ((Place) agent).getType() == PlaceType.PARK) {
					spatial = shapeFactory.createImage(park);
				}
				else if (agent instanceof Human && ((Human) agent).getJob().getJobType() == JobType.DRIVER) { 
					// TODO: choisir la bonne direction pour la voiture
					Human d = (Human) agent;
					if (ContextCreator.getPlaceAt(d.getX(), d.getY()).getType() == PlaceType.ROAD)
						spatial = shapeFactory.createImage(h_car);
					else
						spatial = shapeFactory.createImage(driver);
				}
				else if (agent instanceof Human && ((Human) agent).getJob().getJobType() == JobType.FARMER) {
				// TODO: choisir la bonne direction pour la voiture
					spatial = shapeFactory.createImage(farmer);
				}
				else if (agent instanceof Human && ((Human) agent).getJob().getJobType() == JobType.SELLER) {
				// TODO: choisir la bonne direction pour la voiture
					spatial = shapeFactory.createImage(seller);
				}
				else if (agent instanceof Human && ((Human) agent).getJob().getJobType() == JobType.BANKER) {
				// TODO: choisir la bonne direction pour la voiture
					spatial = shapeFactory.createImage(banker);
				}
				else if (agent instanceof Human && ((Human) agent).getJob().getJobType() == JobType.TEACHER) {
				// TODO: choisir la bonne direction pour la voiture
					spatial = shapeFactory.createImage(teacher);
				}
				else if (agent instanceof Human && ((Human) agent).getJob().getJobType() == JobType.STUDENT) {
				// TODO: choisir la bonne direction pour la voiture
					spatial = shapeFactory.createImage(student_m);
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			
				if (spatial == null)
					System.out.println("Cannot find image for " + agent);
			} catch (IOException e) {
			// TODO Auto-generated catch block
				System.out.println("in the exception state");
				e.printStackTrace();
			}
		}
		return spatial;
	}	
	
}
