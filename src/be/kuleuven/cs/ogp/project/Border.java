package be.kuleuven.cs.ogp.project;

/**
 * This class specifies an abstract base class for all borders that can be placed on a square.
 *
 * @author  Frederic Hannes
 */
public abstract class Border {

    /**
     * Returns true if a character can pass through this border.
     */
    public abstract boolean canPassThrough();

    /**
     * Returns true if the border is slippery.
     */
    public abstract boolean isSlippery();

}
