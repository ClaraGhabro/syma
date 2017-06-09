package context;

import java.util.Arrays;
import java.util.List;

public class Constants {
	// Prices
	public final static double schoolPrice = 0.5;
	public final static double farmerPrice = 0.6;
	
	// Durations
	public final static int schoolDuration = 10;
	public final static int tradeDuration = 10;
	
	// Others
	public final static int average = 50;
	public final static int maxAge = 20;
	public final static int hungerPerUnit = 5;
	public final static int sleepEnergy = 15;
	public final static int minimumFood = 30;
	public final static int maximumFood = 1000;
	public final static int minimumHunger = 10;
	public final static int foodHunger = 10;
	public final static int moodHappy = 15;
	public final static int maxMoodHappy = 75;
	public final static int maxSleepHappy = 75;
	public final static int maxFoodHappy = 75;
	public final static int reproducingEnergy = -5;
	public final static int reproduceCount = 5;
	
	// Counts
	public final static int updateTick = 100;
	public final static int yearCount = 360;
	public final static int fieldCount = 3;
	public final static int gestationCount = 5;
	
	// Names
	public final static List<String> namesMale = Arrays.asList("André", "Théodore", "Marc", "Joseph", "Bertrand", "Anthony", "Stephane", "Bruce", "Arthur", "Sebastien", "Guillaume", "Anatole", "Justin", "Xavier", "Gilbert", "Jonathan", "Paul");
	public final static List<String> namesFemale = Arrays.asList("Julie", "Juliette", "Patricia", "Marion", "Marine", "Laure", "Anne", "Aïcha", "Brigite", "Lea", "Clara", "Justine", "Josephine", "Josianne", "Marie", "Claire");
}