package com.kylecorry.tf;

/**
 * The position of a point in 3D
 */
public class Point {
    public double x, y, z;
    private static final double EPSILON = 0.000000000000001;

    public static final Point origin = new Point(0, 0, 0);

    /**
     * Create a point in 3D space.
     *
     * @param x The x position of the point.
     * @param y The y position of the point.
     * @param z The z position of the point.
     */
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a point from the cylindrical coordinate system.
     *
     * @param r     The magnitude of the X-Y component.
     * @param theta The angle around the Z axis in radians.
     * @param z     The z value.
     * @return The cartesian representation of the point in the cylindrical coordinate system.
     */
    public static Point fromCylindrical(double r, double theta, double z) {
        double x = r * Math.cos(theta);
        double y = r * Math.sin(theta);
        return new Point(x, y, z);
    }

    /**
     * Add two points together.
     *
     * @param transform The point to add.
     * @return A point which is the addition of this point and the other point.
     */
    public Point add(Point transform) {
        return new Point(x + transform.x, y + transform.y, z + transform.z);
    }

    /**
     * Move a point by a scalar.
     *
     * @param transform The scalar to add to the point.
     * @return The point moved along each of its axes by the scalar.
     */
    public Point add(double transform) {
        return new Point(x + transform, y + transform, z + transform);
    }

    /**
     * Move a point by a vector.
     *
     * @param transform The vector to move to the point by.
     * @return The point moved by the vector.
     */
    public Point add(Vector3 transform) {
        return new Point(x + transform.x, y + transform.y, z + transform.z);
    }

    /**
     * Subtract two points.
     *
     * @param transform The point to subtract.
     * @return A point which is the difference of this point and the other point.
     */
    public Point subtract(Point transform) {
        return new Point(x - transform.x, y - transform.y, z - transform.z);
    }

    /**
     * Move a point in the opposite direction by a scalar.
     *
     * @param transform The scalar to subtract from the point.
     * @return The point moved along each of its axes by the scalar in the opposite direction.
     */
    public Point subtract(double transform) {
        return new Point(x - transform, y - transform, z - transform);
    }

    /**
     * Move a point by a vector in the opposite direction.
     *
     * @param transform The vector to move to the point by.
     * @return The point moved by the vector in the opposite direction.
     */
    public Point subtract(Vector3 transform) {
        return new Point(x - transform.x, y - transform.y, z - transform.z);
    }

    /**
     * Scale the point by a scalar.
     *
     * @param scaleFactor The scale factor.
     * @return The point scaled by the scale factor.
     */
    public Point multiply(double scaleFactor) {
        return new Point(x * scaleFactor, y * scaleFactor, z * scaleFactor);
    }

    /**
     * Scale the point by a scalar (1/scalar).
     *
     * @param scaleFactor The scale factor which will be inverted.
     * @return The point scaled by the inverted scale factor.
     */
    public Point divide(double scaleFactor) {
        return new Point(x / scaleFactor, y / scaleFactor, z / scaleFactor);
    }

    /**
     * Convert the point to a vector from the origin.
     *
     * @return The vector representation of the point.
     */
    public Vector3 toVector() {
        return new Vector3(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Point))
            return false;
        Point other = (Point) obj;
        return Math.abs(other.x - x) <= EPSILON && Math.abs(other.y - y) <= EPSILON && Math.abs(other.z - z) <= EPSILON;
    }

    @Override
    public String toString() {
        return "<Point " + x + ", " + y + ", " + z + ">";
    }

}
