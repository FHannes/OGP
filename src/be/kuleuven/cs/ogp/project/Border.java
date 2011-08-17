package be.kuleuven.cs.ogp.project;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;

/**
 * This class specifies an abstract base class for all borders that can be placed on a square.
 *
 * @author  Frederic Hannes
 */
public abstract class Border {

    /**
     * The first square this border is linked to.
     */
    private Square square1;

    /**
     * The second square this border is linked to.
     */
    private Square square2;

    /**
     * Returns true if a character can pass through this border.
     */
    public abstract boolean canPassThrough();

    /**
     * Returns true if the border is slippery.
     */
    public abstract boolean isSlippery();

    /**
     * Returns true if the given border will override this border when merged.
     */
    public abstract boolean overridden(Border border);

    /**
     * Return the first square the border is linked to.
     */
    @Basic @Model
    private Square getSquare1() {
        return square1;
    }

    /**
     * Set the first square the border is linked to.
     * @param   square1
     *          The given square.
     * @post    The given square equals the first square for the border.
     *          | new.getSquare1() == square1
     */
    @Basic
    private void setSquare1(Square square1) {
        this.square1 = square1;
    }

    /**
     * Return the second square the border is linked to.
     */
    @Basic @Model
    private Square getSquare2() {
        return square2;
    }

    /**
     * Set the second square the border is linked to.
     * @param   square2
     *          The given square.
     * @post    The given square equals the second square for the border.
     *          | new.getSquare1() == square1
     */
    @Basic
    private void setSquare2(Square square2) {
        this.square2 = square2;
    }

    /**
     * Links a border to a square or it's adjacent partner.
     *
     * @param   square
     *          The given square to link.
     * @post    If the border was not yet linked to a square adn the given square is valid, the given square is linked
     *          to it.
     *          | if ((square != null) && (getSquare1() == null))
     *          |   new.getSquare1() == square
     * @post    If the border had a square linked to it and the given square is valid, the given direction is valid and
     *          is next to the first square, it will be linked to it.
     *          | if ((square != null) && (getSquare2() == null) &&
     *          |   (this.getSquare1().getPos().equals(dir.move(square.getPos())))
     *          |   new.getSquare2() == square
     */
    protected void link(Square square, Direction dir) throws IllegalArgumentException {
        if (square != null)
            if (getSquare1() == null)
                setSquare1(square);
            else if ((getSquare2() == null) && (dir != null)) {
                if (this.getSquare1().getPos().equals(dir.move(square.getPos()))) {
                    setSquare2(square);
                // TODO: Check border overriding

                }
            }
    }
}
