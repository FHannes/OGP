package be.kuleuven.cs.ogp.project.squares;

import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.ogp.project.borders.Door;

/**
 * This class represents a transparent square.
 *
 * @author Frederic Hannes
 */
public class Transparent extends Square {

    /**
     * Creates an instance of a rock square.
     *
     * @param   doorDir
     *          The given direction for the door.
     * @param   twoDoors
     *          The flag indicating whether the square should have 2 doors or not.
     * @effect  Sets the temperature to 0.
     *          | setTemp(0)
     * @effect  Sets the humidity to 0.
     *          | setHumidity(0)
     * @effect  Sets the floor's slippery flag to false.
     *          | setSlipperyFloor(false)
     * @effect  Adds a door in the given direction.
     *          | setBorder(new Door(false), doorDir)
     * @effect  Adds a second door in the opposite direction of the given direction if the second door flag is true.
     *          | if (twoDoors)
     *          |   setBorder(new Door(false), doorDir.opposite())
     */
    public Transparent(Direction doorDir, boolean twoDoors) {
        setTemp(0);
        setHumidity(0);
        setSlipperyFloor(false);
        setBorder(new Door(false), doorDir);
        if (twoDoors)
            setBorder(new Door(false), doorDir.opposite());
        setRestricted(true);
    }

    /**
     * Returns true if you can change the borders of the square.
     */
    @Override
    public boolean canChangeBorder() {
        return !isRestricted();
    }

}
