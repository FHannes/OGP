package be.kuleuven.cs.ogp.project.borders;

import be.kuleuven.cs.ogp.project.Border;
import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * This class represents a border with a door in it.
 *
 * @author Frederic Hannes
 */
public class Door extends Border {

    /**
     * The state of the door.
     */
    private boolean opened;

    /**
     * Creates a new instance of this class.
     *
     * @param   opened
     *          The given state for the door.
     * @effect  The state of the door for the new object equals the given state.
     *          | setOpened(opened);
     */
    public Door(boolean opened) {
        setOpened(opened);
    }

    /**
     * Returns true if a character can pass through this border.
     */
    @Override @Raw
    public boolean isOpen() {
        return opened;
    }

    /**
     * Returns true if the border is slippery.
     */
    @Override
    public boolean isSlippery() {
        return false;
    }

    /**
     * Returns true if the given border will override this border when merged.
     */
    @Override
    public boolean overridden(Border border) {
        return (border instanceof Wall);
    }

    /**
     * Checks whether the border can be linked to a given square in a given direction.
     *
     * @param   square
     *          The given square.
     * @param   dir
     *          The given direction
     * @return  False if the given direction is the floor of the square.
     *          | if (dir == Direction.FLOOR)
     *          | result == false
     * @return  True if the square contains less than 3 doors.
     */
    @Override
    public boolean canLink(Square square, Direction dir) {
        if (dir == Direction.FLOOR)
            return false;
        int count = 0;
        for (Direction d : Direction.values()) {
            Border border = square.getBorder(dir);
            if (border instanceof Door)
                count++;
        }
        return count < 3;
    }

    /**
     * Opens or closes the door in this border.
     *
     * @param   opened
     *          The given opened state.
     * @post    When opened, one can pass through the door, else one cannot.
     *          | new.isOpen() == opened
     */
    @Basic
    public void setOpened(boolean opened) {
        this.opened = opened;
        if (getAdjacent() != null)
            ((Door) getAdjacent()).opened = opened;
    }

    /**
     * Creates and returns a copy of this object.
     */
    @Override
    public Object clone() {
        return new Door(isOpen());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "Door(opened:" + isOpen() + ")";
    }

}
