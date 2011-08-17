package be.kuleuven.cs.ogp.project.borders;

import be.kuleuven.cs.ogp.project.Border;

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
    public boolean canPassThrough() {
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
     * Creates and returns a copy of this object.
     */
    @Override
    public Object clone() {
        return new Wall(isSlippery());
    }

}
