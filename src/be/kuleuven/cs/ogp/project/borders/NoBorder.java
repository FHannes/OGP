package be.kuleuven.cs.ogp.project.borders;

import be.kuleuven.cs.ogp.project.Border;

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
    public boolean canPassThrough() {
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

}