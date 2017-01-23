package com.kylecorry.tf;

public class Pose {
	public Point position;
	public Quaternion orientation;

	public Pose(Point position, Quaternion orientation) {
		this.position = position;
		this.orientation = orientation;
	}
}
