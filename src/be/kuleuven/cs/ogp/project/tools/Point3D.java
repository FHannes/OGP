package be.kuleuven.cs.ogp.project.tools;

import be.kuleuven.cs.som.annotate.Basic;

/**
 * This class holds a 3 dimensional coordinate set.
 *
 * @author	Frederic Hannes
 */
public class Point3D {

	private long x;
	private long y;
	private long z;

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
	public Point3D(long x, long y, long z) {
		setX(x);
		setY(y);
		setZ(z);
	}

	/**
	 * Return the x-coordinate stored in the object.
	 */
	@Basic
	public long getX() {
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
	public void setX(long x) {
		this.x = x;
	}

	/**
	 * Return the y-coordinate stored in the object.
	 */
	@Basic
	public long getY() {
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
	public void setY(long y) {
		this.y = y;
	}

	/**
	 * Return the z-coordinate stored in the object.
	 */
	@Basic
	public long getZ() {
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
	public void setZ(long z) {
		this.z = z;
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
}
