package be.kuleuven.cs.ogp.project;

import be.kuleuven.cs.ogp.project.tools.Point3D;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;

import java.util.HashMap;
import java.util.Map;

/**
 * The class containing the most basic dungeon type.
 *
 * @invar   The X dimension of the dungeon can never be larger than the maximum allowed X dimension.
 *          | this.getXDim() <= this.getXDimMax()
 * @invar   The Y dimension of the dungeon can never be larger than the maximum allowed Y dimension.
 *          | this.getYDim() <= this.getYDimMax()
 * @invar   The Z dimension of the dungeon can never be larger than the maximum allowed Z dimension.
 *          | this.getZDim() <= this.getZDimMax()
 *
 * @author  Frederic Hannes
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
     * The map containing all of the squares in the dungeon.
     */
    private Map<Point3D, Square> squares = new HashMap<>();

    /**
     * Creates a new instance of dungeon.
     *
     * @effect  Sets the maximum dungeon dimensions to maximum value for Long.
     *          | setXDimMax(Long.MAX_VALUE)
     *          | setYDimMax(Long.MAX_VALUE)
     *          | setZDimMax(Long.MAX_VALUE)
     * @effect  Sets the current dungeon dimensions to 0.
     *          | setXDim(0)
     *          | setYDim(0)
     *          | setZDim(0)
     */
    public Dungeon() {
        setXDimMax(Long.MAX_VALUE);
        setYDimMax(Long.MAX_VALUE);
        setZDimMax(Long.MAX_VALUE);
        setXDim(0);
        setYDim(0);
        setZDim(0);
    }

    /**
     * Returns the maximum size for the X dimension.
     */
    @Basic @Raw
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
    @Model @Raw
    protected void setXDimMax(long xDimMax) {
        this.xDimMax = xDimMax;
    }

    /**
     * Returns the maximum size for the Y dimension.
     */
    @Basic @Raw
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
    @Model @Raw
    protected void setYDimMax(long yDimMax) {
        this.yDimMax = yDimMax;
    }

    /**
     * Returns the maximum size for the Z dimension.
     */
    @Basic @Raw
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
    @Model @Raw
    protected void setZDimMax(long zDimMax) {
        this.zDimMax = zDimMax;
    }

    /**
     * Returns the size of the X dimension of the dungeon.
     */
    @Basic @Raw
    public long getXDim() {
        return xDim;
    }

    /**
     * Sets the current X-dimension size.
     *
     * @param   xDim
     *          The given size.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the new dimension is smaller than the old one.
     *          | xDim < this.getXDim()
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the new dimension is larger than the maximum allowed size.
     *          | xDim > this.getXDimMax()
     */
    @Model @Raw
    private void setXDim(long xDim) throws IllegalArgumentException {
        if (xDim < this.getXDim())
            throw new IllegalArgumentException("The new X dimension has to be larger than the old one!");
        if (xDim > this.getXDimMax())
            throw new IllegalArgumentException("The new X dimension is larger than the maximum allowed size!");
        this.xDim = xDim;
    }

    /**
     * Returns the size of the Y dimension of the dungeon.
     */
    @Basic @Raw
    public long getYDim() {
        return yDim;
    }

    /**
     * Sets the current Y-dimension size.
     *
     * @param   yDim
     *          The given size.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the new dimension is smaller than the old one.
     *          | yDim < this.getYDim()
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the new dimension is larger than the maximum allowed size.
     *          | yDim > this.getYDimMax()
     */
    @Model @Raw
    public void setYDim(long yDim) throws IllegalArgumentException {
        if (yDim < this.getYDim())
            throw new IllegalArgumentException("The new Y dimension has to be larger than the old one!");
        if (yDim > this.getYDimMax())
            throw new IllegalArgumentException("The new Y dimension is larger than the maximum allowed size!");
        this.yDim = yDim;
    }

    /**
     * Returns the size of the Z dimension of the dungeon.
     */
    @Basic @Raw
    public long getZDim() {
        return zDim;
    }

    /**
     * Sets the current Z-dimension size.
     *
     * @param   zDim
     *          The given size.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the new dimension is smaller than the old one.
     *          | zDim < this.getZDim()
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the new dimension is larger than the maximum allowed size.
     *          | zDim > this.getZDimMax()
     */
    @Model @Raw
    public void setZDim(long zDim) throws IllegalArgumentException {
        if (zDim < this.getZDim())
            throw new IllegalArgumentException("The new Z dimension has to be larger than the old one!");
        if (zDim > this.getZDimMax())
            throw new IllegalArgumentException("The new Z dimension is larger than the maximum allowed size!");
        this.zDim = zDim;
    }

    /**
     * Adds a square to the dungeon.
     *
     * @param   square
     * @param   pos
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given square is invalid.
     */
    public void addSquare(Square square, Point3D pos) throws IllegalArgumentException {
        if (square == null)
            throw new IllegalArgumentException("Invalid square!");
        // TODO: Finish
    }

}
