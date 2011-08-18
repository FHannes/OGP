package be.kuleuven.cs.ogp.project.borders;

import be.kuleuven.cs.ogp.project.Border;
import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Square;

/**
 * This class specifies a border made out of plastic foil which can be torn to open the border.
 *
 * @author Frederic Hannes
 */
public class PlasticFoil extends Border {

    private boolean torn = false;

    /**
     * Returns true if a character can pass through this border.
     */
    @Override
    public boolean isOpen() {
        return torn;
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
        return (border instanceof Wall) ||
                (border instanceof Door);
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
     * Tears the plastic foil for this border.
     */
    public void tear() {
        torn = true;
        if (getAdjacent() != null)
            ((PlasticFoil) getAdjacent()).torn = true;
    }

    /**
     * Creates and returns a copy of this object.
     */
    @Override
    public Object clone() {
        PlasticFoil pf = new PlasticFoil();
        if (isOpen())
            pf.tear();
        return pf;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "PlasticFoil(torn:" + isOpen() + ")";
    }

}
