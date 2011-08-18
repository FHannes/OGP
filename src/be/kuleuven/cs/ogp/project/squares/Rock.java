package be.kuleuven.cs.ogp.project.squares;

import be.kuleuven.cs.ogp.project.Border;
import be.kuleuven.cs.ogp.project.Direction;
import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.ogp.project.borders.Wall;

/**
 * This class represents a rock square.
 *
 * @author  Frederic Hannes
 */
public class Rock extends Square {

    public Rock() {
        setTemp(0);
        setHumidity(0);
        setSlipperyFloor(false);
        for (Direction dir : Direction.values())
            setBorder(new Wall(false), dir);
        setRestricted(true);
    }

    /**
     * Returns true if you can change the temperature of the square.
     */
    @Override
    public boolean canChangeTemp() {
        return !isRestricted();
    }

    /**
     * Returns true if you can change the humidity of the square.
     */
    @Override
    public boolean canChangeHumidity() {
        return !isRestricted();
    }

    /**
     * Returns true if you can change the borders of the square.
     */
    @Override
    public boolean canChangeBorder() {
        return !isRestricted();
    }

    /**
     * Is called when the square is linked to a neighbour.
     */
    @Override
    protected void linked() {
        double humidity = 0;
        int count = 0;
        for (Direction dir : Direction.values()) {
            Border border = getBorder(dir);
            if (border.getAdjacent() != null) {
                humidity += border.getAdjacent().getSquare().getHumidity();
                count++;
            }
        }
        if (count > 0) {
            setRestricted(false);
            setHumidity(humidity / count);
            setRestricted(true);
        }
    }

    /**
     * Returns true if the square is solid and can not contain anything.
     */
    @Override
    public boolean isSolid() {
        return true;
    }
}
