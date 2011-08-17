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
    private boolean open;

    /**
     * Creates a new instance of this class.
     *
     * @param   open
     *          The given state for the door.
     * @effect  The state of the door for the new object equals the given state.
     *          | setOpen(open);
     */
    public Door(boolean open) {
        setOpen(open);
    }

    /**
     * Returns true if a character can pass through this border.
     */
    @Override @Raw
    public boolean canPassThrough() {
        return open;
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
     * @param   open
     *          The given open state.
     * @post    When opened, one can pass through the door, else one cannot.
     *          | new.canPassThrough() == open
     */
    @Basic
    public void setOpen(boolean open) {
        this.open = open;
        if (getAdjacent() != null)
            ((Door) getAdjacent()).setOpen(open);
    }

    /**
     * Creates and returns a copy of this object.
     */
    @Override
    public Object clone() {
        return new Door(canPassThrough());
    }

}
