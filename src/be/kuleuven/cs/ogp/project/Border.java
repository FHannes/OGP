package be.kuleuven.cs.ogp.project;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class specifies an abstract base class for all borders that can be placed on a square.
 *
 * @author  Frederic Hannes
 */
public abstract class Border {

    /**
     * The square this adjacent is linked to.
     */
    private Square square;

    /**
     * The opposite adjacent this adjacent is linked to.
     */
    private Border adjacent;

    /**
     * Returns true if a character can pass through this adjacent.
     */
    public abstract boolean isOpen();

    /**
     * Returns true if the adjacent is slippery.
     */
    public abstract boolean isSlippery();

    /**
     * Returns true if the given adjacent will override this adjacent when merged.
     */
    public abstract boolean overridden(Border border);

    /**
     * Return the square the adjacent is linked to.
     */
    @Basic
    public Square getSquare() {
        return square;
    }

    /**
     * Set the square the adjacent is linked to.
     * @param   square
     *          The given square.
     * @post    The given square equals the square for the adjacent.
     *          | new.getSquare() == square
     */
    @Basic
    void setSquare(Square square) {
        this.square = square;
    }

    /**
     * Returns true if the adjacent has been assigned to a square.
     */
    public boolean assigned() {
        return getSquare() != null;
    }

    /**
     * Returns the adjacent on the other side of the edge between it's square and the adjacent one.
     */
    @Basic
    public Border getAdjacent() {
        return adjacent;
    }

    /**
     * Sets the adjacent found on the other side of the edge between a square and the adjacent one.
     *
     * @param   adjacent
     *          The given adjacent.
     * @post    The new adjacent equals the given adjacent.
     *          | new.getAdjacent() == adjacent
     */
    @Basic
    void setAdjacent(Border adjacent) {
        this.adjacent = adjacent;
    }

    /**
     * Creates and returns a copy of this object.
     */
    @Override
    public abstract Object clone();
}
