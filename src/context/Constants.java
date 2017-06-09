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
	public final static int hungerPerUnit = 3;
	public final static int sleepEnergy = 10;
	public final static int foodHunger = 10;
	public final static int moodHappy = 10;
	public final static int maxMoodHappy = 80;
	public final static int maxSleepHappy = 80;
	public final static int reproducingEnergy = -10;
	public final static int reproduceCount = 3;
	
	// Counts
	public final static int updateTick = 10;
	public final static int yearCount = 30;
	public final static int fieldCount = 3;
	public final static int gestationCount = 10;
	
	// Names
	public final static List<String> namesMale = Arrays.asList("Guillaume", "Anatole", "Justin", "Xavier", "Gilbert", "Jonathan", "Paul");
	public final static List<String> namesFemale = Arrays.asList("Lea", "Clara", "Justine", "Josephine", "Josianne", "Marie", "Claire");
}