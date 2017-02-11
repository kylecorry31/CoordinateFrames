package com.kylecorry.tf;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import com.kylecorry.geometry.Point;
import com.kylecorry.geometry.Pose;
import com.kylecorry.geometry.Quaternion;
import com.kylecorry.geometry.Vector3;
import org.junit.Test;

public class Examples {

    private static final double EPSILON = 0.00000001;

    @Test
    public void testTransforms() {
        TransformationMap tf = new TransformationMap();
        tf.put("Test", new Pose(new Point(1, 2, 3), new Quaternion(0, Vector3.k)));
        assertEquals(new Transform(new Vector3(-1, -2, -3), new Quaternion(0, Vector3.k)), tf.lookup("Test"));
        tf.put("Testing", "Test", new Pose(new Point(0, 0, 0), new Quaternion(Math.PI / 2, Vector3.k)));
        assertEquals(new Transform(new Vector3(0, 0, 0), new Quaternion(-Math.PI / 2, Vector3.k)), tf.lookup("Testing"));
        assertTrue(pointApproxEqual(new Point(0, 0, 0), tf.transformToOrigin(new Point(-1, -2, -3), "Test"), EPSILON));
        assertTrue(pointApproxEqual(new Point(2, -1, -3), tf.transform(new Point(-1, -2, -3), "Testing", "Test"), EPSILON));
        assertTrue(pointApproxEqual(new Point(3, 1, 0), tf.transformToOrigin(new Point(-1, -2, -3), "Testing"), EPSILON));
        assertTrue(pointApproxEqual(new Point(0, 0, 0), tf.transform(new Point(0, 0, 0), "Testing", "Test"), EPSILON));
    }

    private boolean pointApproxEqual(Point p1, Point p2, double diff) {
        return Math.abs(p1.x - p2.x) <= diff && Math.abs(p1.y - p2.y) <= diff && Math.abs(p1.z - p2.z) <= diff;
    }
}
