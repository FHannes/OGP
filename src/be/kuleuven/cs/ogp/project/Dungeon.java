package be.kuleuven.cs.ogp.project;

import be.kuleuven.cs.ogp.project.squares.Rock;
import be.kuleuven.cs.ogp.project.squares.Teleport;
import be.kuleuven.cs.ogp.project.tools.Point3D;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import sun.rmi.log.LogInputStream;

import java.util.*;

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
public class Dungeon<T extends Square> {

    /**
     * The maximum allowed fraction of squares with a slippery floor in the dungeon.
     */
    private static final double MAX_SLIPPERY = 0.2; // 0 <= MAX_SLIPPERY <= 1

    /**
     * The composite dungeon which contains this dungeon.
     */
    private CompositeDungeon<T> dungeon = null;

    /**
     * The position of this dungeon in it's parent dungeon.
     */
    private Point3D pos = null;

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
    private long xDim = 0;

    /**
     * The size of the Y dimension.
     */
    private long yDim = 0;

    /**
     * The size of the Z dimension.
     */
    private long zDim = 0;

    /**
     * The map containing all of the squares in the dungeon.
     */
    private Map<Point3D, T> squares = new HashMap<>();

    /**
     * Creates a new instance of dungeon.
     *
     * @effect  Sets the maximum dungeon dimensions to the maximum value for Long.
     *          | setXDimMax(Long.MAX_VALUE)
     *          | setYDimMax(Long.MAX_VALUE)
     *          | setZDimMax(Long.MAX_VALUE)
     */
    public Dungeon() {
        setXDimMax(Long.MAX_VALUE);
        setYDimMax(Long.MAX_VALUE);
        setZDimMax(Long.MAX_VALUE);
    }

    /**
     * Returns the maximum size for the X dimension.
     */
    @Basic @Raw
    public final long getXDimMax() {
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
    protected final void setXDimMax(long xDimMax) {
        this.xDimMax = xDimMax;
    }

    /**
     * Returns the maximum size for the Y dimension.
     */
    @Basic @Raw
    public final long getYDimMax() {
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
    protected final void setYDimMax(long yDimMax) {
        this.yDimMax = yDimMax;
    }

    /**
     * Returns the maximum size for the Z dimension.
     */
    @Basic @Raw
    public final long getZDimMax() {
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
    protected final void setZDimMax(long zDimMax) {
        this.zDimMax = zDimMax;
    }

    /**
     * Returns the size of the X dimension of the dungeon.
     */
    @Basic @Raw
    public final long getXDim() {
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
    public final long getYDim() {
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
    private final void setYDim(long yDim) throws IllegalArgumentException {
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
    public final long getZDim() {
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
    private final void setZDim(long zDim) throws IllegalArgumentException {
        if (zDim < this.getZDim())
            throw new IllegalArgumentException("The new Z dimension has to be larger than the old one!");
        if (zDim > this.getZDimMax())
            throw new IllegalArgumentException("The new Z dimension is larger than the maximum allowed size!");
        this.zDim = zDim;
    }

    /**
     * Checks whether a given position is valid for use with the dungeon class.
     *
     * @param   pos
     *          The given position.
     * @return  True if the position is valid.
     *          | result == !((pos == null) || ((pos.getX() == pos.getY()) && (pos.getY() == pos.getZ())) ||
     *          |   (pos.getY() < 0) || (pos.getZ() < 0))
     */
    public static boolean isValidPos(Point3D pos) {
        return !((pos == null) || ((pos.getX() == pos.getY()) && (pos.getY() == pos.getZ())) || (pos.getX() < 0) ||
            (pos.getY() < 0) || (pos.getZ() < 0));
    }

    /**
     * Returns the map containing all of the dungeon's squares.
     */
    @Model
    private Map<Point3D, T> getSquares() {
        return squares;
    }

    /**
     * Checks whether a square is present in the dungeon at the provided location.
     *
     * @param   pos
     *          The given position.
     * @return  Returns false if pos is invalid or if the position does not contain a square.
     *          | result == ((pos != null) && this.getSquares().containsKey(pos))
     */
    public boolean hasSquare(Point3D pos) {
        return (pos != null) && this.getSquares().containsKey(pos);
    }

    /**
     * Gets the square at a certain position.
     *
     * @param   pos
     *          The given position.
     * @return  Returns null if the position is invalid or no square is assigned to it in the dungeon.
     *          | if (!hasSquare(pos))
     *          |   result == null
     * @return  Returns the square square at the given position.
     *          | result == this.getSquares().get(pos)
     */
    public T getSquare(Point3D pos) {
        if (!hasSquare(pos))
            return null;
        return this.getSquares().get(pos);
    }

    /**
     * Adds a square to the dungeon.
     *
     * @param   square
     *          The given square.
     * @param   pos
     *          The given position for the square.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given square is invalid.
     *          | square == null
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given square is already part of a dungeon.
     *          | square.getDungeonAt() != null
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given position is invalid.
     *          | !isValidPos(pos)
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if a square exists at the given position.
     *          | this.getSquares().containsKey(pos)
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the maximum allowed number of squares with a slippery floor is
     *          already present in the dungeon.
     *          | (slipperyCount / getSquares().size()) > MAX_SLIPPERY
     * @effect  Increases all dimensions to the required size if the given position does not fall inside of the current
     *          dimensions.
     *          | if (pos.getX() >= this.getXDim())
     *          |   this.setXDim(pos.getX() + 1);
     *          | if (pos.getY() >= this.getYDim())
     *          |   this.setYDim(pos.getY() + 1);
     *          | if (pos.getZ() >= this.getZDim())
     *          |   this.setZDim(pos.getZ() + 1);
     * @post    The given square is now part of the dungeon.
     *          | new.getSquare(pos).equals(square) == true
     * @effect  The square is assigned to the dungeon and can not be assigned to any other dungeon afterwards.
     *          | square.setDungeon(this)
     *          | square.setPos(pos)
     */
    public void addSquare(T square, Point3D pos) throws IllegalArgumentException {
        if (square == null)
            throw new IllegalArgumentException("Invalid square!");
        if (square.getDungeon() != null)
            throw new IllegalArgumentException("The given square is already part of a dungeon!");
        if (!isValidPos(pos))
            throw new IllegalArgumentException("Invalid position!");
        if (this.getSquares().containsKey(pos))
            throw new IllegalArgumentException("A square exists at the given position!");
        if (square.isSlipperyFloor()) {
            int slipperyCount = 0;
            for (Square sq : getSquares().values())
                if (sq.isSlipperyFloor())
                    slipperyCount++;
            if ((slipperyCount / getSquares().size()) > MAX_SLIPPERY)
                throw new IllegalArgumentException("Maximum allowed tiles with a slippery floor is already present!");
        }
        // Add square to dungeon
        if (pos.getX() >= this.getXDim())
            this.setXDim(pos.getX() + 1);
        if (pos.getY() >= this.getYDim())
            this.setYDim(pos.getY() + 1);
        if (pos.getZ() >= this.getZDim())
            this.setZDim(pos.getZ() + 1);
        getSquares().put((Point3D) pos.clone(), square);
        square.setDungeon(this);
        square.setPos(pos);
        // Link squares
        for (Direction dir : Direction.values()) {
            Square neighbour = getSquare(dir.move(pos));
            if (neighbour != null)
                square.link(neighbour, dir);
        }
    }

    /**
     * Removes the square at a given position and returns it.
     *
     * @param   pos
     *          The given position.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given position is invalid.
     *          | !isValidPos(pos)
     * @return  The square that was removed from the dungeon.
     *          result == getSquare(pos)
     */
    public T removeSquare(Point3D pos) throws IllegalArgumentException {
        if (!isValidPos(pos))
            throw new IllegalArgumentException("Invalid position!");
        T old = getSquare(pos);
        if (old != null) {
            old.unlink();
            getSquares().remove(pos);
        }
        return old;
    }

    /**
     * Checks whether a coordinate is inside of the dungeon's dimensions.
     *
     * @param   pos
     *          The given coordinate.
     * @return  True if the coordinate is found inside of the given dimensions.
     *          | result == isValidPos(pos) && (pos.getX() < getXDim()) && (pos.getY() < getYDim()) && (pos.getZ() <
     *          |   getZDim())
     */
    public boolean insideDimensions(Point3D pos) {
        return isValidPos(pos) && (pos.getX() < getXDim()) && (pos.getY() < getYDim()) && (pos.getZ() < getZDim());
    }

    /**
     * Fills a list with all squares belonging to a space at a given position. Time complexity of O(6n).
     *
     * @param   space
     *          The given space list.
     * @param   pos
     *          The given position.
     * @param   teleports
     *          The flag which indicates whether or not to include teleport squares.
     * @effect  If there's no square at the given position, the square is already part of the space or the given
     *          position falls outside of the bounds of the dungeon, the method exits.
     *          | if (sq == null || space.contains(sq) || !insideDimensions(pos))
     *          |   return
     * @effect  If the square is found and is not part of the space, it is added to the space?
     *          | space.add(sq)
     * @effect  All neighbours are being checked to see if they are part of the space.
     *          | for (Direction dir : Direction.values()) {
     *          |   next = dir.move(pos)
     *          |   neighbour = getSquare(next)
     *          |   if ((neighbour != null) && (sq.getBorder(dir).isOpen()))
     *          |       getSpace(space, next, teleports) }
     * @effect  If the teleport flag is set and the square at the given position is a teleport square, the spaces of the
     *          destinations of the teleport are added to the space.
     *          | if (teleports && (sq instanceof Teleport))
     *          |   for (square : ((Teleport) sq).getDest())
     *          |       getSpace(space, square.getPos(), teleports)
     */
    @Model
    private void getSpace(List<Square> space, Point3D pos, boolean teleports) {
        if (!insideDimensions(pos))
            return;
        Square sq = getSquare(pos);
        if (sq == null || space.contains(sq))
            return;
        space.add(sq);
        for (Direction dir : Direction.values()) {
            Point3D next = dir.move(pos);
            Square neighbour = getSquare(next);
            if ((neighbour != null) && (sq.getBorder(dir).isOpen()))
                getSpace(space, next, teleports);
        }
        if (teleports && (sq instanceof Teleport))
            for (Square square : ((Teleport) sq).getDest())
                getSpace(space, square.getPos(), teleports);
    }

    /**
     * Creates a list containing all squares belonging to a space at a given position.
     *
     * @param   pos
     *          The given position.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception when the given position are invalid.
     *          | !isValidPos(pos)
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception when the given position does not match a square.
     * @return  The list with the space.
     *          | space = new ArrayList<>()
     *          | getSpace(space, pos)
     *          | result == space
     */
    public List<Square> getSpace(Point3D pos) {
        if (!isValidPos(pos))
            throw new IllegalArgumentException("Invalid position!");
        if (!hasSquare(pos))
            throw new IllegalArgumentException("There's no square at the given position!");
        List<Square> space = new ArrayList<>();
        getSpace(space, pos, false);
        return space;
    }

    /**
     * Creates a list containing all squares belonging to a space at a given position and all spaces linked recursively
     * to teleport squares in that space.
     *
     * @param   pos
     *          The given position.
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception when the given position are invalid.
     *          | !isValidPos(pos)
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception when the given position does not match a square.
     * @return  The list with the space.
     *          | space = new ArrayList<>()
     *          | getSpace(space, pos)
     *          | result == space
     */
    public List<Square> getTeleSpace(Point3D pos) {
        if (!isValidPos(pos))
            throw new IllegalArgumentException("Invalid position!");
        if (!hasSquare(pos))
            throw new IllegalArgumentException("There's no square at the given position!");
        List<Square> space = new ArrayList<>();
        getSpace(space, pos, true);
        return space;
    }

    /**
     * Returns the parent dungeon of the given dungeon. If the dungeon does not have a parent dungeon, the method
     * returns null.
     */
    @Basic
    public CompositeDungeon<T> getDungeon() {
        return dungeon;
    }

    /**
     * Sets the parent dungeon for the dungeon.
     *
     * @param   dungeon
     *          The given dungeon.
     * @post    The new parent dungeon equals the given dungeon.
     *          | new.getDungeon() == dungeon
     */
    @Basic
    protected void setDungeon(CompositeDungeon<T> dungeon) {
        this.dungeon = dungeon;
    }

    /**
     * Returns the relative position of the dungeon in it's parent dungeon. If the dungeon does not have a parent
     * dungeon, the method returns null.
     */
    public Point3D getPos() {
        if (pos == null)
            return null;
        else
            return (Point3D) pos.clone();
    }

    /**
     * Returns the absolute position of the dungeon on the playing field. If the dungeon does not have a parent dungeon,
     * the method returns null.
     *
     * @result  If the dungeon has a parent dungeon, the absolute position is returned, recursively factoring in that
     *          parent.
     *          | if ((this.getDungeonAt() != null) && (this.getPos() != null))
     *          |   result == this.getDungeonAt().getPos().add(this.getPos())
     * @result  If the dungeon does not have a parent dungeon, null is returned.
     *          | if ((this.getDungeonAt() == null) || (this.getPos() == null))
     *          |   result == null;
     */
    public Point3D getAbsolutePos() {
        if ((this.getDungeon() != null) && (this.getPos() != null))
            return this.getDungeon().getPos().add(this.getPos());
        else return null;
    }

    /**
     * Sets the position of the dungeon in it's parent dungeon.
     *
     * @param   pos
     *          The given position.
     * @post    The new position equals the given position.
     *          | new.getPos() == pos
     */
    @Basic
    protected void setPos(Point3D pos) {
        this.pos = pos;
    }

    /**
     * Returns an iterator to iterate over all squares in the dungeon.
     */
    public Iterator<Square> iterator() {
        return (Iterator<Square>) getSquares().values().iterator();
    }

    /**
     * Internal method to fill a list with all teleport squares present in the dungeon.
     */
    protected void getTeleports(List<TeleportInterface> teleports) {
        for (Square sq : getSquares().values())
            if (sq instanceof TeleportInterface)
                teleports.add((TeleportInterface) sq);

    }

    /**
     * Returns a list of all teleports in the dungeon recursively.
     */
    public List<TeleportInterface> getTeleports() {
        List<TeleportInterface> teleports = new ArrayList<>();
        getTeleports(teleports);
        return teleports;
    }

    /**
     * Returns a list containing all rock squares in the dungeon with a temperature of at least 200°C.
     *
     * @return  The list containing the hot rock squares.
     *          | squares = new ArrayList<>()
     *          |   for (sq : getSquares().values())
     *          |       if ((sq instanceof Rock) && (sq.getTemp() >= 200))
     *          |           squares.add(sq)
     *          | result == squares
     */
    public List<Square> getHotRockSquares() {
        List<Square> squares = new ArrayList<>();
        for (Square sq : getSquares().values())
            if ((sq instanceof Rock) && (sq.getTemp() >= 200))
                squares.add(sq);
        return squares;
    }

}
