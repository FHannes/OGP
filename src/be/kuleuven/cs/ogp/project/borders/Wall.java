package be.kuleuven.cs.ogp.project.borders;

import be.kuleuven.cs.ogp.project.Border;
import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Square;

/**
 * This class specifies a wall border which can be placed on a square.
 *
 * @author  Frederic Hannes
 */
public class Wall extends Border {

    /**
     * Stores whether the wall is slippery, can only be set once.
     */
    public final boolean slippery;

    /**
     * Creates a new wall border object.
     *
     * @param   slippery
     *          Given slippery state.
     * @post    The new object's slippery state equals the given one.
     *          | new.isSlippery() == slippery
     */
    public Wall(boolean slippery) {
        this.slippery = slippery;
    }

    /**
     * Returns true if a character can pass through this border.
     */
    @Override
    public boolean isOpen() {
        return false;
    }

    /**
     * Returns true if the border is slippery.
     */
    @Override
    public boolean isSlippery() {
        return slippery;
    }

    /**
     * Returns true if the given border will override this border when merged.
     */
    @Override
    public boolean overridden(Border border) {
        return false;
    }

    /**
     * Checks whether the border can be linked to a given square in a given direction.
     *
     * @param   square
     *          The given square.
     * @param   dir
     *          The given direction
     * @return  True if the border can be linked to the square in the given direction.
     */
    @Override
    public boolean canLink(Square square, Direction dir) {
        return true;
    }

    /**
     * Creates and returns a copy of this object.
     */
    @Override
    public Object clone() {
        return new Wall(isSlippery());
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "Wall(slippery:" + isSlippery() + ")";
    }

}
