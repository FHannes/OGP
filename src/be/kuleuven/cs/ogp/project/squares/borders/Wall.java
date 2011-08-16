package be.kuleuven.cs.ogp.project.squares.borders;

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

}
