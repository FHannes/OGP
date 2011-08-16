/**
 * An enumeration specifying a temperature unit.
 * 
 * @author	Frederic Hannes
 */
public enum Temperature {
	CELSIUS,
	KELVIN {

		/**
		 * Converts a temperature value in degrees celsius to kelvin.
		 * 
		 * @param	value
		 * 			The given temperature value.
		 * @return	The converted temperature value.
		 * 			| result == Math.floor(super.convertTo(value) - 273.15)
		 */
		@Override
		public int convertTo(double value) {
			return (int) Math.floor(super.convertTo(value) + 273.15);
		}

		/**
		 * Converts a temperature value in kelvin to degrees celsius.
		 * 
		 * @param	value
		 * 			The given temperature value.
		 * @return	The converted temperature value.
		 * 			| result == super.convertFrom(value - 273.15)
		 */
		@Override
		public int convertFrom(double value) {
			return super.convertFrom(value - 273.15);
		}
		
	},
	FAHRENHEIT {

		/**
		 * Converts a temperature value in degrees celsius to degrees
		 * fahrenheit.
		 * 
		 * @param	value
		 * 			The given temperature value.
		 * @return	The converted temperature value.
		 * 			| result == Math.floor(super.convertTo(value) * 9 / 5 + 32)
		 */
		@Override
		public int convertTo(double value) {
			return (int) Math.floor(super.convertTo(value) * 9 / 5 + 32);
		}
		
		/**
		 * Converts a temperature value in degrees fahrenheit to degrees
		 * celsius.
		 * 
		 * @param	value
		 * 			The given temperature value.
		 * @return	The converted temperature value.
		 * 			| result == super.convertFrom((value - 32) * 5 / 9)
		 */
		@Override
		public int convertFrom(double value) {
			return super.convertFrom((value - 32) * 5 / 9);
		}
		
	};
	
	/**
	 * Converts a temperature value in degrees celsius to the unit specified
	 * by the enumeration object. This method by default returns the value
	 * for a conversion to degrees celsius, it is overridden for the other
	 * units.
	 * 
	 * @param	value
	 * 			The given temperature value.
	 * @throws	IllegalArgumentException
	 * 			Throws an illegal argument exception if the given
	 * 			temperature value is invalid.
	 * 			| value < -273.15
	 * @return	The converted temperature value.
	 * 			| result == Math.floor(value)
	 */
	public int convertTo(double value) throws IllegalArgumentException {
		if (value < -273.15)
			throw new IllegalArgumentException("Invalid temperature value");
		return (int) Math.floor(value);
	}
	
	/**
	 * Converts a temperature value from the unit specified by the
	 * enumeration object to degrees celsius. This method by default returns
	 * the value for a conversion from degrees celsius, it is overridden for
	 * the other units.
	 * 
	 * @param	value
	 * 			The given temperature value.
	 * @throws	IllegalArgumentException
	 * 			Throws an illegal argument exception if the given
	 * 			temperature value is invalid.
	 * 			| value < -273.15
	 * @return	The converted temperature value.
	 * 			| result == Math.ceil(value)
	 */
	public int convertFrom(double value) throws IllegalArgumentException {
		if (value < -273.15)
			throw new IllegalArgumentException("Invalid temperature value");
		return (int) Math.ceil(value);
	}
	
}