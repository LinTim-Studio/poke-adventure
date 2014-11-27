package pokeAdventure.util;

import java.io.Serializable;


public class Vector2i implements Serializable {

	/** The version ID for this class  */
	private static final long serialVersionUID = -3153813463308278483L;
	
	/** The x component of this vector */
	public int x;
	/** The y component of this vector */
	public int y;
	
	/**
	 * Create an empty vector
	 */
	public Vector2i() {
	}

	/**
	 * Create a vector based on the contents of a coordinate array
	 * 
	 * @param coords The coordinates array, index 0 = x, index 1 = y
	 */
	public Vector2i(int[] coords) {
		x = coords[0];
		y = coords[1];
	}
	
	/**
	 * Get the angle this vector is at
	 * 
	 * @return The angle this vector is at (in degrees)
	 */
	public double getTheta() {
		double theta = StrictMath.toDegrees(StrictMath.atan2(y, x));
		if ((theta < -360) || (theta > 360)) {
			theta = theta % 360;
		}
		if (theta < 0) {
			theta = 360 + theta;
		}

		return theta;
	} 
	
	/**
	 * Create a new vector based on another
	 * 
	 * @param other The other vector to copy into this one
	 */
	public Vector2i(Vector2i other) {
		this(other.x,other.y);
	}
	
	/**
	 * Create a new vector 
	 * 
	 * @param x The x component to assign
	 * @param y The y component to assign
	 */
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Set the value of this vector
	 * 
	 * @param other The values to set into the vector
	 */
	public void set(Vector2i other) {
		set(other.x,other.y);
	}
	
	/**
	 * Dot this vector against another
	 * 
	 * @param other The other vector dot against
	 * @return The dot product of the two vectors
	 */
	public int dot(Vector2i other) {
		return (x * other.x) + (y * other.y);
	}
	
	/**
	 * Set the values in this vector
	 * 
	 * @param x The x component to set
	 * @param y The y component to set
	 * @return This vector - useful for chaining operations
	 */
	public Vector2i set(int x, int y) { 
		this.x = x; 
		this.y = y; 
		
		return this;
	}
	
	/**
	 * A vector perpendicular to this vector.
	 *
	 * @return a vector perpendicular to this vector
	 */
	public Vector2i getPerpendicular() {
	   return new Vector2i(-y, x);
	}
	
	/**
	 * Set the values in this vector
	 * 
	 * @param pt The pair of values to set into the vector
	 * @return This vector - useful for chaining operations
	 */
	public Vector2i set(int[] pt) {
		return set(pt[0], pt[1]);
	}
	
	/**
	 * Negate this vector 
	 * 
	 * @return A copy of this vector negated
	 */
	public Vector2i negate() {
		return new Vector2i(-x, -y); 
	}

	/**
	 * Negate this vector without creating a new copy
	 * 
	 * @return This vector - useful for chaining operations
	 */
	public Vector2i negateLocal() {
		x = -x;
		y = -y;
		
		return this;
	}
	
	/**
	 * Add a vector to this vector
	 * 
	 * @param v The vector to add
	 * @return This vector - useful for chaining operations
	 */
	public Vector2i add(Vector2i v)
	{
		x += v.x; 
		y += v.y;
		
		return this;
	}
	
	/**
	 * Subtract a vector from this vector
	 * 
	 * @param v The vector subtract
	 * @return This vector - useful for chaining operations
	 */
	public Vector2i sub(Vector2i v)
	{
		x -= v.x; 
		y -= v.y;
		
		return this;
	}

	/**
	 * Scale this vector by a value
	 * 
	 * @param a The value to scale this vector by
	 * @return This vector - useful for chaining operations
	 */
	public Vector2i scale(int a)
	{
		x *= a; 
		y *= a;
		
		return this;
	}
    
	/**
	 * The length of the vector squared
	 * 
	 * @return The length of the vector squared
	 */
	public int lengthSquared() {
		return (x * x) + (y * y);
	}
	
	/**
	 * Get the length of this vector
	 * 
	 * @return The length of this vector
	 */
	public float length() 
	{
		return (float) Math.sqrt(lengthSquared());
	}
	
	/**
	 * Return a copy of this vector
	 * 
	 * @return The new instance that copies this vector
	 */
	public Vector2i copy() {
		return new Vector2i(x,y);
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[Vector2i "+x+","+y+" ("+length()+")]";
	}
	
	/**
	 * Get the distance from this point to another
	 * 
	 * @param other The other point we're measuring to
	 * @return The distance to the other point
	 */
	public float distance(Vector2i other) {
		return (float) Math.sqrt(distanceSquared(other));
	}
	
	/**
	 * Get the distance from this point to another, squared. This
	 * can sometimes be used in place of distance and avoids the 
	 * additional sqrt.
	 * 
	 * @param other The other point we're measuring to 
	 * @return The distance to the other point squared
	 */
	public int distanceSquared(Vector2i other) {
		int dx = other.x - x;
		int dy = other.y - y;
		
		return (dx*dx)+(dy*dy);
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
        return 997 * ((int)x) ^ 991 * ((int)y); //large primes! 
	}
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (other instanceof Vector2i) {
			Vector2i o = ((Vector2i) other);
			return (o.x == x) && (o.y == y);
		}
		
		return false;
	}
}
