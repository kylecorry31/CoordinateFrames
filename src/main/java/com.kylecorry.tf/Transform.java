package com.kylecorry.tf;

import com.kylecorry.geometry.Quaternion;
import com.kylecorry.geometry.Vector3;

/**
 * A representation of a transform in 3D space.
 */
public class Transform {
    public Vector3 translation;
    public Quaternion rotation;

    /**
     * Create a transform with a translation and rotation.
     *
     * @param translation The translation portion of the transform.
     * @param rotation    The rotation portion of the transform.
     */
    public Transform(Vector3 translation, Quaternion rotation) {
        this.translation = translation;
        this.rotation = rotation;
    }

    /**
     * Computes the inverse of the transform.
     *
     * @return The inverse transform.
     */
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
