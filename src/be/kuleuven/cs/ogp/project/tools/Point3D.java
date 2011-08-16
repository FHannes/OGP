package be.kuleuven.cs.ogp.project.tools;

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
	
}
