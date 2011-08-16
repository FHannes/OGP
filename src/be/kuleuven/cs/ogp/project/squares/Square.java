package be.kuleuven.cs.ogp.project.squares;

import be.kuleuven.cs.ogp.project.tools.Tools;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * This class represents a square or tile in the game.
 *
 * @invar  The temperature assigned to the square can not be smaller than -200°C or larger than 5000°C.
 *         | (this.getTemp() >= -200) && (this.getTemp() <= 5000)
 * @invar  The humidity value assigned to the square can never be smaller than 0 or larger than 100.
 *         | (this.getHumidity() >= 0) && (this.getHumidity() <= 100)
 *
 * @author Frederic Hannes (http://www.freddy1990.net/)
 */

public class Square {

    /**
     * The minimum temperature below which cold damage can occur.
     */
    @Model
    private static final int DMG_COLD_MIN = -5;

    /**
     * The number of degrees below the minimum for cold damage required to receive a damage point.
     */
    @Model
    private static final int DMG_COLD_INTERVAL = 10;

    /**
     * The maximum temperature above which heat damage can occur.
     */
    private static int DMG_HEAT_MAX = 35;

    /**
     * The number of degrees above the maximum for heat damage required to
     * receive a damage point.
     */
    private static int DMG_HEAT_INTERVAL = 15;

    /**
     * The maximum humidity above which rust damage can occur.
     */
    @Model
    private static final int DMG_RUST_MAX = 30;

    /**
     * The percentage above the maximum for rust damage required to receive a damage point.
     */
    @Model
    private static final int DMG_RUST_INTERVAL = 7;

    /**
     * The weight used to calculate the new temperature of 2 tiles when they are being merged.
     */
    @Model
    private static final double MERGE_WEIGHT = 0.2; // 0.1 <= MERGE_WEIGHT <= 0.4

    /**
     * The border identifier constant for the ceiling border.
     */
    public static final int BORDER_CEILING = 1;

    /**
     * The border identifier constant for the north border.
     */
    public static final int BORDER_NORTH = 2;

    /**
     * The border identifier constant for the east border.
     */
    public static final int BORDER_EAST = 4;

    /**
     * The border identifier constant for the south border.
     */
    public static final int BORDER_SOUTH = 8;

    /**
     * The border identifier constant for the west border.
     */
    public static final int BORDER_WEST = 16;

    /**
     * The border identifier constant for the floor border.
     */
    public static final int BORDER_FLOOR = 32;

    /**
     * The maximum number of border identifier values.
     */
    @Model
    private static final int BORDER_MAX = BORDER_FLOOR * 2;

    /**
     * Stores the temperature of the square in degrees Celsius.
     */
    private int temp;

    /**
     * Stores the humidity of the square as a percentage with an accuracy of 2
     * decimals.
     */
    private double humidity;

    /**
     * Equals true if the floor of the square is slippery.
     */
    private boolean slipperyFloor;

    /**
     * Contains all of the borders of the square.
     */
    private int borders;

    /**
     * Creates a new instance of a square with a given temperature and humidity.
     *
     * @param   temp
     *          The given temperature.
     * @param   humidity
     *          The given humidity.
     * @effect  Sets the given temperature as the object's temperature.
     *          | setTemp(temp)
     * @effect  Sets the given humidity as the object's humidity.
     *          | setHumidity(humidity)
     * @effect  Sets the floor to being not slippery by default.
     *          | setSlipperyFloor(false)
     * @effect  Sets the borders to being all inactive by default.
     *          | setBorders(0)
     */
    public Square(int temp, double humidity) {
        setTemp(temp);
        setHumidity(humidity);
        setSlipperyFloor(false);
        setBorders(0);
    }

    /**
     * Creates a new instance of a square.
     *
     * @effect  Sets the given temperature to 0°C by default.
     *          | setTemp(0)
     * @effect  Sets the given humidity 0 by default.
     *          | setHumidity(0)
     * @effect  Sets the floor to being not slippery by default.
     *          | setSlipperyFloor(false)
     * @effect  Sets the borders to being all inactive by default.
     *          | setBorders(0)
     */
    public Square() {
        setTemp(0);
        setHumidity(0);
        setSlipperyFloor(false);
        setBorders(0);
    }

    /**
     * Returns the temperature assigned to the object.
     */
    @Basic @Raw
    public int getTemp() {
        return temp;
    }

    /**
     * Assigns a new temperature to the object given by temperature.
     *
     * @post    The new temperature of the object equals the given temperature.
     *          | new.getTemperature() == temp
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception when the given temperature is not valid.
     *          | !isValidTemp(temp)
     */
    @Basic
    public void setTemp(int temp) throws IllegalArgumentException {
        if (isValidTemp(temp))
            this.temp = temp;
        else
            throw new IllegalArgumentException("Invalid temperature given!");
    }

    /**
     * Checks whether the temperature is a valid one to be stored in an object of Square. The temperature is valid if
     * it's not smaller than -200°C and not larger than 5000°C.
     *
     * @param    temp
     *           The given temperature.
     * @return   True if the temperature is valid.
     *           | result == (temp >= -200) && (temp <= 5000)
     */
    public static boolean isValidTemp(int temp) {
        return (temp >= -200) && (temp <= 5000);
    }

    /**
     * Returns the cold damage received on this square.
     *
     * @return  Returns 0 if the temperature is above the cold damage temperature boundary.
     *          | if (getTemp() > DMG_COLD_MIN)
     *          |   result == 0
     * @return  If the temperature is below the cold damage temperature boundary, the number of damage points received
     *          is calculated and returned.
     *          | if (getTemp() <= DMG_COLD_MIN)
     *          |   result == Math.floor((getTemp() - DMG_COLD_MIN) / DMG_COLD_INTERVAL);
     */
    @Raw
    public int getColdDmg() {
        if (getTemp() <= DMG_COLD_MIN)
            return (int) Math.floor((0 - (getTemp() - DMG_COLD_MIN)) / DMG_COLD_INTERVAL);
        else
            return 0;
    }

    /**
     * Returns the maximum temperature allowed before heat damage can occur.
     */
    @Basic
    public static int getDmgHeatMax() {
        return DMG_HEAT_MAX;
    }

    /**
     * Sets the new maximum temperature above which heat damage can occur for all squares.
     *
     * @param   temp
     *          The given new temperature.
     * @post    The new maximum temperature equals the given temperature.
     *          | new.getDmgHeatMax() = temp
     */
    @Basic
    public static void setDmgHeatMax(int temp) {
        DMG_HEAT_MAX = temp;
    }

    /**
     * Returns the interval after which a heat damage point is received.
     */
    @Basic
    public static int getDmgHeatInterval() {
        return DMG_HEAT_INTERVAL;
    }

    /**
     * Sets the interval after which a heat damage point is received for all squares.
     *
     * @param   interval
     *          The new temperature interval.
     * @post    The new temperature interval equals the given interval.
     *          | new.getDmgHeatInterval() == interval
     */
    @Basic
    public static void setDmgHeatInterval(int interval) {
        DMG_HEAT_INTERVAL = interval;
    }


    /**
     * Returns the heat damage received on this square.
     *
     * @return  Returns 0 if the temperature is below the heat damage temperature boundary.
     *          | if (getTemp() < DMG_HEAT_MAX)
     *          |   result == 0;
     * @return  If the temperature is above the heat damage temperature boundary, returns the number of damage points
     *          received is calculated and returned.
     *          | if (getTemp() >= DMG_HEAT_MAX)
     *          |   result == Math.floor((getTemp() - DMG_HEAT_MAX) / DMG_HEAT_INTERVAL)
     */
    @Raw
    public int getHeatDmg() {
        if (getTemp() >= DMG_HEAT_MAX)
            return (int) Math.floor((getTemp() - DMG_HEAT_MAX) / DMG_HEAT_INTERVAL);
        else
            return 0;
    }

    /**
     * Returns the humidity of the square. The humidity is returned as a percentage with an accuracy of 2 decimals.
     */
    @Basic @Raw
    public double getHumidity() {
        return humidity;
    }

    /**
     * Sets the current humidity of the square.
     *
     * @pre     The value for humidity must be valid.
     *          | isValidHumidity(humidity) == true
     * @param   humidity
     *          The given humidity.
     * @post    The new humidity equals the given humidity.
     *          | new.getHumidity() == humidity
     */
    public void setHumidity(double humidity) {
        assert(isValidHumidity(humidity));
        this.humidity = Tools.roundTo(humidity, 2);
    }

    /**
     * Checks whether a given value for humidity is valid.
     *
     * @param   humidity
     *          The given humidity.
     * @return  True if the humidity value is valid. The value is valid if it's not smaller than 0 or larger than 100.
     *          | result == ((humidity >= 0) && (humidity <= 100))
     */
    public static boolean isValidHumidity(double humidity) {
        return (humidity >= 0) && (humidity <= 100);
    }

    /**
     * Calculates the damage received by rust from this square based on the
     * current humidity.
     *
     * @return  Returns 0 if the humidity is smaller than the maximum humidity under which no rust damage will occur.
     *          | if (getTemp() < DMG_RUST_MAX)
     *          |   result == 0
     * @return  If the humidity is larger than the maximum humidity under which no rust damage will occur, the rust
     *          damage is calculated with 1 point for each specific interval above this level.
     *          | if (getTemp() >= DMG_RUST_MAX)
     *          |   result == Math.floor((getTemp() - DMG_RUST_MAX) / DMG_RUST_INTERVAL)
     */
    @Raw
    public int getRustDmg() {
        if (getTemp() >= DMG_RUST_MAX)
            return (int) Math.floor((getTemp() - DMG_RUST_MAX) / DMG_RUST_INTERVAL);
        else
            return 0;
    }

    /**
     * Returns true if the floor of the square is slippery.
     */
    @Basic
    public boolean isSlipperyFloor() {
        return slipperyFloor;
    }

    /**
     * Sets the slippery indicator for the floor of the square.
     *
     * @param   slipperyFloor
     *          The given slippery indicator.
     * @post    The new slippery value of the square equals the given value.
     *          | new.isSlipperyFloor() == slipperyFloor
     */
    @Basic
    public void setSlipperyFloor(boolean slipperyFloor) {
        this.slipperyFloor = slipperyFloor;
    }

    /**
     * Determines whether the square is slippery.
     *
     * @return  Returns true if the square is slippery because of a slippery floor, or when the humidity of the square
     *          is 100% and the    temperature is larger than 0°C, or when the humidity is larger than 10% and the
     *          temperature of the square is not positive.
     *          | result == isSlipperyFloor() || ((getHumidity() == 100) && (getTemp() > 0)) || ((getHumidity() > 10)
     *          |   && (getTemp() <= 0));
     */
    @Raw
    public boolean isSlippery() {
        return isSlipperyFloor() || ((getHumidity() == 100) && (getTemp() > 0)) || ((getHumidity() > 10) &&
                (getTemp() <= 0));
    }

    /**
     * Calculates the inhabitability of a square.
     *
     * @return  The inhabitability index of the square after being calculated with a specific formula.
     *          | result == - (Math.sqrt(Math.pow(getHeatDmg(), 3)) / Math.sqrt(101 - getHumidity()) +
     *          |   Math.sqrt(getColdDmg()));
     */
    @Raw
    public double getInhabitability() {
        return - (Math.sqrt(Math.pow(getHeatDmg(), 3)) / Math.sqrt(101 - getHumidity()) + Math.sqrt(getColdDmg()));
    }

    /**
     * Returns the field containing the bitmask which indicates which borders are active on the square.
     */
    @Basic @Raw
    public int getBorders() {
        return borders;
    }

    /**
     * Sets the value for the field containing the bitmask which indicates which borders are present on the square.
     *
     * @param   borders
     *          The given borders bitmask value.
     * @post    If the given borders value is smaller than 0, all borders will be deactivated.
     *          | if (borders < 0)
     *          |   new.getBorders() == 0;
     * @post    If the given borders value is larger than the maximum possible borders value, all borders will be
     *          activated.
     *          | if (borders >= BORDER_MAX)
     *          |   new.getBorders() == (BORDER_MAX - 1)
     * @post    If the given borders value is valid, it will be set as the current value.
     *          | if ((borders >= 0) && (borders < BORDER_MAX))
     *          |   new.getBorders() == borders
     */
    public void setBorders(int borders) {
        if (borders < 0)
            this.borders = 0;
        else if (borders >= BORDER_MAX)
            this.borders = BORDER_MAX - 1;
        else
            this.borders = borders;
    }

    /**
     * Checks whether the square has one or more borders.
     *
     * @param   border
     *          The border bitmask.
     * @return  If the given border bitmask is invalid, the method returns false.
     *          | if (!isValidBorder(border))
     *          |   result == false
     * @return  If the given border bitmask is valid, the method returns true if the borders specified by the value are
     *          active.
     *          | if (isValidBorder(border))
     *          |   result == ((getBorders() & border) != 0)
     * @note    Multiple borders can be specified in the bitmask using the bitwise or operator to combine several border
     *          bitmasks.
     */
    @Raw
    public boolean hasBorder(int border) {
        if (!isValidBorder(border))
            return false;
        else
            return (getBorders() & border) != 0;
    }

    /**
     * Sets one or more borders specified by a bitmask to active or non-active.
     *
     * @param   border
     *          The border bitmask.
     * @param   active
     *          True if the border has to be set to active.
     * @effect  If the given border bitmask is valid and the border has to be activated, all borders specified by the
     *          bitmask border are added.
     *          | if (isValidBorder(border) && active)
     *          |   setBorders(getBorders() | border)
     * @effect  If the given border bitmask is valid and the border has to be deactivated, all borders specified by the
     *          bitmask border are removed.
     *          | if (isValidBorder(border) && !active)
     *          |   setBorders(getBorders() & -border)
     */
    public void setBorder(int border, boolean active) {
        if (isValidBorder(border))
            if (active)
                setBorders(getBorders() | border);
            else
                setBorders(getBorders() & -border);
    }

    /**
     * Checks whether a given border bitmask is valid.
     *
     * @param   border
     *          The given border identifier.
     * @return  True if the given value is valid.
     *          | result == ((border >= 0) && (border < BORDER_MAX))
     */
    public static boolean isValidBorder(int border) {
        return (border >= 0) && (border < BORDER_MAX);
    }

    /**
     * Merges the properties of 2 squares at a certain border.
     *
     * @param   square
     *          The given square.
     * @param   border
     *          The given border.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given square is invalid.
     *          | square == null
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given square is the current square object.
     *          | square.equals(this)
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given border bitmask is invalid.
     *          | !isValidBorder(border)
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given border bitmask does not equal a single border.
     *          | !Tools.isPow2(border)
     * @effect  The given border is removed for the current and given square.
     *          | this.setBorder(border, false)
     *          | square.setBorder(border, false)
     * @effect  Both squares are assigned the arithmic mean of their humidities.
     *          | newHumidity = (getHumidity() + square.getHumidity()) / 2
     *          | this.setHumidity(newHumidity)
     *          | square.setHumidity(newHumidity)
     * @effect  Calculates a weight for the temperature of both squares and uses these to calculate a new temperature.
     *          | weight = 2 * (1 - MERGE_WEIGHT) * this.getHumidity() / (square.getHumidity() + this.getHumidity())
     *          | newTemp = ((2 - weight) * square.getTemp() + weight * this.getTemp()) / 2
     *          | square.setTemp(Math.round(newTemp))
     *          | this.setTemp(Math.round(newTemp))
     */
    @Raw
    public void mergeWith(Square square, int border) throws IllegalArgumentException {
        if (square == null)
            throw new IllegalArgumentException("Invalid square!");
        if (square.equals(this))
            throw new IllegalArgumentException("A square can not be merged with itself!");
        if (!isValidBorder(border))
            throw new IllegalArgumentException("The given border bitmask is not valid!");
        if (!Tools.isPow2(border))
            throw new IllegalArgumentException("The given border bitmask does not equal a single border!");

        this.setBorder(border, false);
        square.setBorder(border, false);

        double newHumidity = (getHumidity() + square.getHumidity()) / 2;
        this.setHumidity(newHumidity);
        square.setHumidity(newHumidity);

        double weight = 2 * (1 - MERGE_WEIGHT) * this.getHumidity() / (square.getHumidity() + this.getHumidity());
        double newTemp = ((2 - weight) * square.getTemp() + weight * this.getTemp()) / 2;
        square.setTemp((int) Math.round(newTemp));
        this.setTemp((int) Math.round(newTemp));
    }

}