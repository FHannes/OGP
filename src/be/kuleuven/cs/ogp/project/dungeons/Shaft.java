package be.kuleuven.cs.ogp.project.dungeons;

import be.kuleuven.cs.ogp.project.Dungeon;

/**
 * This class represents a shaft type dungeon.
 *
 * @invar   The X dimension of a shaft is always 1.
 *          | this.getXDim() == 1
 * @invar   The Y dimension of a shaft is always 1.
 *          | this.getYDim() == 1
 *
 * @author Frederic Hannes
 */
public class Shaft extends Dungeon {

    /**
     * Creates a new instance of a shaft dungeon.
     *
     * @effect  Sets the maximum dungeon dimensions to A, except for the Z dimension which is set to the maximum value
     *          for Long.
     *          | setXDimMax(1)
     *          | setYDimMax(1)
     *          | setZDimMax(Long.MAX_VALUE)
     */
    public Shaft() {
        setXDimMax(1);
        setYDimMax(1);
        setZDimMax(Long.MAX_VALUE);
    }

}
