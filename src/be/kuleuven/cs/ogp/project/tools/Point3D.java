package be.kuleuven.cs.ogp.project.tools;

import be.kuleuven.cs.ogp.project.Square;
import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class holds a 3 dimensional coordinate set.
 *
 * @author	Frederic Hannes
 */
public class Point3D {

	private int x;
	private int y;
	private int z;

	/**
	 * Initializes this 3 dimensional point with given x, y and z values.
	 *
	 * @effect	The given x-coordinate is set as the new x-coordinate.
	 * 			| setX(x)
	 * @effect	The given y-coordinate is set as the new y-coordinate.
	 * 			| setY(y)
	 * @effect	The given z-coordinate is set as the new z-coordinate.
	 * 			| setZ(z)
	 */
	public Point3D(int x, int y, int z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	/**
	 * Return the x-coordinate stored in the object.
	 */
	@Basic
	public int getX() {
		return x;
	}

	/**
	 * Set the x-coordinate for this object to the given x-coordinate.
	 *
	 * @param	x
	 * 			The given x-coordinate.
	 * @post	The new x-coordinate equals the given x-coordinate.
	 * 			| new.getX() == x
	 */
	@Basic
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Return the y-coordinate stored in the object.
	 */
	@Basic
	public int getY() {
		return y;
	}

	/**
	 * Set the y-coordinate for this object to the given y-coordinate.
	 *
	 * @param	y
	 * 			The given y-coordinate.
	 * @post	The new y-coordinate equals the given y-coordinate.
	 * 			| new.getY() == y
	 */
	@Basic
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Return the z-coordinate stored in the object.
	 */
	@Basic
	public int getZ() {
		return z;
	}

	/**
	 * Set the z-coordinate for this object to the given z-coordinate.
	 *
	 * @param	z
	 * 			The given z-coordinate.
	 * @post	The new z-coordinate equals the given z-coordinate.
	 * 			| new.getZ() == z
	 */
	@Basic
	public void setZ(int z) {
		this.z = z;
	}

    /**
     * Calculates the distance between the current point and a given point.
     *
     * @param   pos
     *          The given point.
     * @return  Returns the distance between this point and the given point.
     *          | x = this.getX() + pos.getX()
     *          | y = this.getY() + pos.getY()
     *          | z = this.getZ() + pos.getZ()
     *          | result == Math.sqrt(x * x + y * y + z * z)
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given point is invalid.
     *          | pos == null
     */
    public double dist(Point3D pos) throws IllegalArgumentException {
        if (pos == null)
            throw new IllegalArgumentException("Invalid point!");
        int x = this.getX() + pos.getX();
        int y = this.getY() + pos.getY();
        int z = this.getZ() + pos.getZ();
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Returns a point with the coordinates of the given point added to those of the current point.
     *
     * @param   pos
     *          The given point.
     * @return  The added points.
     *          | res = pos.clone()
     *          | res.setX(this.getX() + res.getX())
     *          | res.setY(this.getY() + res.getY())
     *          | res.setZ(this.getZ() + res.getZ())
     *          | result == res
     */
    public Point3D add(Point3D pos) {
        Point3D res = (Point3D) pos.clone();
        res.setX(this.getX() + res.getX());
        res.setY(this.getY() + res.getY());
        res.setZ(this.getZ() + res.getZ());
        return res;
    }

    /**
     * Returns a point with the coordinates of the given point subtracted from those of the current point.
     *
     * @param   pos
     *          The given point.
     * @return  The subtracted points.
     *          | res = pos.clone()
     *          | res.setX(this.getX() - res.getX())
     *          | res.setY(this.getY() - res.getY())
     *          | res.setZ(this.getZ() - res.getZ())
     *          | result == res
     */
    public Point3D subtract(Point3D pos) {
        Point3D res = (Point3D) pos.clone();
        res.setX(this.getX() - res.getX());
        res.setY(this.getY() - res.getY());
        res.setZ(this.getZ() - res.getZ());
        return res;
    }

    /**
     * Creates and returns a copy of this object.
     *
     * @return  Returns a clone of the object.
     *          | result == new Point3D(getX(), getY(), getZ())
     */
    @Override
    public Object clone() {
        return new Point3D(getX(), getY(), getZ());
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @throws  IllegalArgumentException
     *          Throws an illegal argument exception if the given object is not of the same class.
     *          | !(obj instanceof Point3D)
     * @return  Returns true if the points have equal coordinates.
     *          | result == ((this.getX() == obj.getX()) && (this.getY() == obj.getY()) && (this.getZ() == obj.getZ()))
     */
    @Override
    public boolean equals(Object obj) throws IllegalArgumentException {
        if (!(obj instanceof Point3D))
            throw new IllegalArgumentException("Object of foreign class!");
        Point3D p = (Point3D) obj;
        return (this.getX() == p.getX()) && (this.getY() == p.getY()) && (this.getZ() == p.getZ());
    }

    /**
     * Returns a hash code value for the object.
     *
     * @note    Algorithm from Effective Java by Joshua Bloch.
     */
    @Override
    public int hashCode() {
        int res = 17;
        res = 37 * res + (getX() ^ (getX() >> 32));
        res = 37 * res + (getY() ^ (getY() >> 32));
        res = 37 * res + (getZ() ^ (getZ() >> 32));
        return res;
    }

    /**
     * Returns a string representation of the object.
     */
    @Override
    public String toString() {
        return "Point3D(x:" + getX() + ";y:" + getY() + ";z:" + getZ() + ")";
    }

}
