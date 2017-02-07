package com.kylecorry.tf;

/**
 * A representation of a rotation in 3D space.
 */
public class Quaternion {
    public double x, y, z, w;

    public static final Quaternion zero = new Quaternion(1, 0, 0, 0);

    /**
     * Create a quaternion with the w, x, y, and z components.
     *
     * @param w The w component.
     * @param x The x component.
     * @param y The y component.
     * @param z The z component.
     */
    public Quaternion(double w, double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    /**
     * Create a quaternion from an angle and an axis.
     *
     * @param theta The angle of rotation in radians around an axis.
     * @param axis  The axis of rotation.
     */
    public Quaternion(double theta, Vector3 axis) {
        Vector3 qAxis = axis.normalize().multiply(Math.sin(theta / 2.0));
        w = Math.cos(theta / 2.0);
        x = qAxis.x;
        y = qAxis.y;
        z = qAxis.z;
    }

    /**
     * Add two quaternions together.
     *
     * @param other The other quaternion to add to this one.
     * @return The summation of the quaternions.
     */
    public Quaternion add(Quaternion other) {
        return new Quaternion(w + other.w, x + other.x, y + other.y, z + other.z);
    }

    /**
     * Subtract two quaternions.
     *
     * @param other The quaternion to subtract from this.
     * @return The difference between the two quaternions.
     */
    public Quaternion subtract(Quaternion other) {
        return new Quaternion(w - other.w, x - other.x, y - other.y, z - other.z);
    }

    /**
     * Create the inverse of the quaternion.
     *
     * @return The inverse of this quaternion.
     */
    public Quaternion inverse() {
        double sumOfSquares = w * w + x * x + y * y + z * z;
        return new Quaternion(w/sumOfSquares, -x/sumOfSquares, -y/sumOfSquares, -z/sumOfSquares);
    }

    /**
     * Multiply two quaternions together.
     *
     * @param other The quaternion to multiply with this one.
     * @return The product of the quaternions.
     */
    public Quaternion multiply(Quaternion other) {
        double nW = w * other.w - x * other.x - y * other.y - z * other.z;
        double nX = w * other.x + x * other.w + y * other.z - z * other.y;
        double nY = w * other.y - x * other.z + y * other.w + z * other.x;
        double nZ = w * other.z + x * other.y - y * other.x + z * other.w;
        return new Quaternion(nW, nX, nY, nZ);
    }

    /**
     * Multiply a quaternion by a point.
     *
     * @param point The point to multiply with this quaternion.
     * @return The product of the quaternion and the point.
     */
    public Quaternion multiply(Point point) {
        return multiply(new Quaternion(0, point.x, point.y, point.z));
    }

    /**
     * Multiply a quaternion by an axis as a vector.
     *
     * @param axis The vector to multiply this quaternion by.
     * @return The product of the quaternion and the vector.
     */
    public Quaternion multiply(Vector3 axis) {
        return multiply(new Quaternion(0, axis.x, axis.y, axis.z));
    }

    /**
     * Rotate a point by the quaternion.
     *
     * @param p The point to rotate.
     * @return The point, rotated by the quaternion.
     */
    public Point rotate(Point p) {
        Quaternion out = multiply(p).multiply(inverse());
        return new Point(out.x, out.y, out.z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Quaternion))
            return false;
        Quaternion other = (Quaternion) obj;
        return other.x == x && other.y == y && other.z == z && other.w == w;
    }
}
