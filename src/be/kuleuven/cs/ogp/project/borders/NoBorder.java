package be.kuleuven.cs.ogp.project.borders;

import be.kuleuven.cs.ogp.project.Border;
import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Square;

/**
 * This class identifies an empty border.
 *
 * @author Frederic Hannes
 */
public class NoBorder extends Border {

    /**
     * Returns true if a character can pass through this border.
     */
    @Override
    public boolean isOpen() {
        return true;
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
        return true;
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
        return new NoBorder();
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "NoBorder()";
    }

}
