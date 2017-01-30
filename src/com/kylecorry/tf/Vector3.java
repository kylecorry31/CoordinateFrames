package com.kylecorry.tf;

/**
 * Represents a direction as a 3D vector
 */
public class Vector3 {

    public static final Vector3 i = new Vector3(1, 0, 0);
    public static final Vector3 j = new Vector3(0, 1, 0);
    public static final Vector3 k = new Vector3(0, 0, 1);
    public static final Vector3 zero = new Vector3(0, 0, 0);

    public double x, y, z;

    /**
     * Create a vector from a point.
     *
     * @param p The point to create the vector from.
     */
    public Vector3(Point p) {
        this(p.x, p.y, p.z);
    }

    /**
     * Create a vector from x, y, and z components.
     *
     * @param x The x component of the vector.
     * @param y The y component of the vector.
     * @param z The z component of the vector.
     */
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Add two vectors together.
     *
     * @param other The other vector to add.
     * @return A vector representing the summation of this vector and the other vector.
     */
    public Vector3 add(Vector3 other) {
        return new Vector3(x + other.x, y + other.y, z + other.z);
    }

    /**
     * Move a vector by a scalar.
     *
     * @param other The scalar to move the vector by.
     * @return A vector moved by the scalar along each axis.
     */
    public Vector3 add(double other) {
        return new Vector3(x + other, y + other, z + other);
    }

    /**
     * Subtract two vectors.
     *
     * @param other The vector to subtract from this vector.
     * @return The difference vector between this vector and the other vector.
     */
    public Vector3 subtract(Vector3 other) {
        return new Vector3(x - other.x, y - other.y, z - other.z);
    }

    /**
     * Move a vector in the opposite direction by a scalar.
     *
     * @param other The scalar to move the vector by.
     * @return The vector moved in the opposite direction by the scalar.
     */
    public Vector3 subtract(double other) {
        return new Vector3(x - other, y - other, z - other);
    }

    /**
     * Scale the vector.
     *
     * @param other The factor to scale the vector by.
     * @return The vector scaled by the scale factor.
     */
    public Vector3 multiply(double other) {
        return new Vector3(x * other, y * other, z * other);
    }

    /**
     * Inversely scale the vector.
     *
     * @param other The factor to inversely scale the vector by.
     * @return The vector inversely scaled by the scale factor.
     */
    public Vector3 divide(double other) {
        return new Vector3(x / other, y / other, z / other);
    }

    /**
     * Compute the dot product of this vector and another vector.
     *
     * @param other The vector to dot with this vector.
     * @return The dot product of the two vectors.
     */
    public double dot(Vector3 other) {
        double sum = x * other.x + y * other.y + z * other.z;
        return sum;
    }

    /**
     * Compute the cross product of two vectors. This will result in the vector orthogonal to both vectors.
     *
     * @param other The vector to cross with this vector.
     * @return The cross product of the two vectors.
     */
    public Vector3 cross(Vector3 other) {
        return new Vector3(y * other.z - y * z, x * other.z - z * other.x, x * other.y - y * other.x);
    }

    /**
     * Compute the magnitude of the vector.
     *
     * @return The magnitude of the vector.
     */
    public double magnitude() {
        double squaredSum = x * x + y * y + z * z;
        return Math.sqrt(squaredSum);
    }

    /**
     * Normalize the vector so the magnitude is 1.
     *
     * @return The normalized vector.
     */
    public Vector3 normalize() {
        return divide(magnitude());
    }

    /**
     * Create a vector from a point to another point.
     *
     * @param from The origin of the vector.
     * @param to   The end point of the vector.
     * @return The vector going from the from point to the to point.
     */
    public static Vector3 fromPoints(Point from, Point to) {
        return new Vector3(to.subtract(from));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Vector3)) return false;
        Vector3 other = (Vector3) obj;
        return other.x == x && other.y == y && other.z == z;
    }

    @Override
    public String toString() {
        return String.format("<Vector3 %1$f %2$f %3$f>", x, y, z);
    }

}
