package be.kuleuven.cs.ogp.project.borders;

import be.kuleuven.cs.ogp.project.Border;
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
