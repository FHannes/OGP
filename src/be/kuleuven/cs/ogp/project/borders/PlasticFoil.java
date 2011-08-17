package be.kuleuven.cs.ogp.project.borders;

import be.kuleuven.cs.ogp.project.Border;

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
    public boolean canPassThrough() {
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
     * Tears the plastic foil for this border.
     */
    public void tear() {
        torn = true;
        if (getAdjacent() != null)
            ((PlasticFoil) getAdjacent()).tear();
    }

    /**
     * Creates and returns a copy of this object.
     */
    @Override
    public Object clone() {
        PlasticFoil pf = new PlasticFoil();
        if (canPassThrough())
            pf.tear();
        return pf;
    }

}
