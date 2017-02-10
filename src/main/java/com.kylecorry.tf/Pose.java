package com.kylecorry.tf;

/**
 * A point and orientation in 3D.
 */
public class Pose {
    public Point position;
    public Quaternion orientation;

    /**
     * Create a pose representation a position with an orientation.
     *
     * @param position    The position.
     * @param orientation The orientation as a quaternion.
     */
    public Pose(Point position, Quaternion orientation) {
        this.position = position;
        this.orientation = orientation;
    }
}
