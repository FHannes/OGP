package be.kuleuven.cs.ogp.project;

import be.kuleuven.cs.ogp.project.tools.Point3D;
import be.kuleuven.cs.som.annotate.Model;

import java.util.HashMap;
import java.util.Map;

/**
 * The class containing the most basic dungeon type.
 *
 * @author Frederic Hannes
 */
public class Dungeon {

    /**
     * The maximum size of the X dimension.
     */
    private long xDimMax;

    /**
     * The maximum size of the Y dimension.
     */
    private long yDimMax;

    /**
     * The maximum size of the Z dimension.
     */
    private long zDimMax;

    /**
     * The size of the X dimension.
     */
    private long xDim;

    /**
     * The size of the Y dimension.
     */
    private long yDim;

    /**
     * The size of the Z dimension.
     */
    private long zDim;

    /**
     * Returns the maximum size for the X dimension.
     */
    public long getXDimMax() {
        return xDimMax;
    }

    /**
     * Sets the maximum X dimension.
     *
     * @param   xDimMax
     *          The given maximum dimension.
     * @post    The new maximum dimension equals the given maximum dimension.
     *          | new.getXDimMax() == xDimMax
     */
    @Model
    protected void setXDimMax(long xDimMax) {
        this.xDimMax = xDimMax;
    }

    /**
     * Returns the maximum size for the Y dimension.
     */
    public long getYDimMax() {
        return yDimMax;
    }

    /**
     * Sets the maximum Y dimension.
     *
     * @param   yDimMax
     *          The given maximum dimension.
     * @post    The new maximum dimension equals the given maximum dimension.
     *          | new.getYDimMax() == yDimMax
     */
    @Model
    protected void setYDimMax(long yDimMax) {
        this.yDimMax = yDimMax;
    }

    /**
     * Returns the maximum size for the Z dimension.
     */
    public long getZDimMax() {
        return zDimMax;
    }

    /**
     * Sets the maximum Z dimension.
     *
     * @param   zDimMax
     *          The given maximum dimension.
     * @post    The new maximum dimension equals the given maximum dimension.
     *          | new.getZDimMax() == zDimMax
     */
    @Model
    protected void setZDimMax(long zDimMax) {
        this.zDimMax = zDimMax;
    }

    /**
     * The map containing all of the squares in the dungeon.
     */
    private Map<Point3D, Square> squares = new HashMap<>();

}
