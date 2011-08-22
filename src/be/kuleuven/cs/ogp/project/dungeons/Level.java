package be.kuleuven.cs.ogp.project.dungeons;

import be.kuleuven.cs.ogp.project.Dungeon;
import be.kuleuven.cs.ogp.project.Square;

/**
 * This class represents a level (plateau) type dungeon.
 *
 * @invar   The Z dimension of a plateau is always 1.
 *          | this.getZDim() == 1
 *
 * @author  Frederic Hannes
 */
public class Level<T extends Square> extends Dungeon<T> {

    /**
     * Creates a new instance of a level dungeon.
     *
     * @effect  Sets the maximum dungeon dimensions to the maximum value for Long, except for the Z dimension which is
     *          set to a maximum size of 1.
     *          | setXDimMax(Long.MAX_VALUE)
     *          | setYDimMax(Long.MAX_VALUE)
     *          | setZDimMax(1)
     */
    public Level() {
        setXDimMax(Long.MAX_VALUE);
        setYDimMax(Long.MAX_VALUE);
        setZDimMax(1);
    }

}
