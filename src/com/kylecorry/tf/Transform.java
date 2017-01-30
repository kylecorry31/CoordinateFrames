package com.kylecorry.tf;

public class Transform {
	public Vector3 translation;
	public Quaternion rotation;

	public Transform(Vector3 translation, Quaternion rotation) {
		this.translation = translation;
		this.rotation = rotation;
	}

	public Transform inverse() {
		return new Transform(translation.multiply(-1), rotation.inverse());
	}

	@Override
	public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Transform))
            return false;
        Transform other = (Transform) obj;
        return other.rotation.equals(rotation) && other.translation.equals(translation);
	}
}
