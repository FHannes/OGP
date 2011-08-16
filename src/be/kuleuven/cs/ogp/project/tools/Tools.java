package be.kuleuven.cs.ogp.project.tools;

/**
 * A set of generic static methods that are used in the project.
 * 
 * @author	Frederic Hannes
 */
public class Tools {
	
	/**
	 * Rounds a given number to a specific number of decimal digits.
	 * 
	 * @param	value
	 * 			The given value to round.
	 * @param	digits
	 * 			The given amount of decimal digits to round to.
	 * @return	The rounded number.
	 */
	public static double roundTo(double value, int digits) {
		double mod = Math.pow(10.0, digits);
		return (value * mod) / mod;
	}
	
	/**
	 * Generates a random boolean value.
	 * 
	 * @return	The boolean value.
	 */
	public static boolean randBool() {
		return Math.round(Math.random()) == 1;
	}
	
}