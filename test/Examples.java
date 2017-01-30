import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.kylecorry.tf.*;
import org.junit.Test;

/**
 * Created by Kylec on 1/29/2017.
 */
public class Examples {
    @Test
    public void testPoint() {
        Point point = new Point(1, 2, 3);
        Point otherPoint = new Point(3, 2, 1);

        assertEquals(new Point(0, 0, 0), point.subtract(point));
        assertEquals(new Point(0, 1, 2), point.subtract(1));
        assertEquals(new Point(2, 4, 6), point.add(point));
        assertEquals(new Point(2, 3, 4), point.add(1));
        assertEquals(new Point(2, 4, 6), point.multiply(2));
        assertEquals(Point.origin, point.multiply(0));
        assertNotEquals(point, otherPoint);
        assertEquals(new Point(0.5, 1, 1.5), point.divide(2));
        assertEquals(new Vector3(1, 2, 3), point.toVector());
        assertEquals(new Point(0, 0, 0), point.subtract(new Vector3(1, 2, 3)));
        assertEquals(new Point(2, 4, 6), point.add(new Vector3(1, 2, 3)));

    }

    @Test
    public void testVector3() {
        Vector3 vector3 = new Vector3(1, 2, 3);
        Vector3 otherVector3 = new Vector3(3, 4, 0);

        assertEquals(new Vector3(0, 0, 0), vector3.subtract(vector3));
        assertEquals(new Vector3(0, 1, 2), vector3.subtract(1));
        assertEquals(new Vector3(2, 4, 6), vector3.add(vector3));
        assertEquals(new Vector3(2, 3, 4), vector3.add(1));
        assertEquals(new Vector3(2, 4, 6), vector3.multiply(2));
        assertEquals(Vector3.zero, vector3.multiply(0));
        assertNotEquals(vector3, otherVector3);
        assertEquals(new Vector3(0.5, 1, 1.5), vector3.divide(2));
        assertEquals(new Vector3(1, 2, 3), new Vector3(new Point(1, 2, 3)));
        assertEquals(new Vector3(1, 0, 0), Vector3.i);
        assertEquals(new Vector3(0, 1, 0), Vector3.j);
        assertEquals(new Vector3(0, 0, 1), Vector3.k);
        assertEquals(11, vector3.dot(otherVector3), 0);
        assertEquals(0, Vector3.i.dot(Vector3.k), 0);
        assertEquals(Vector3.i, Vector3.j.cross(Vector3.k));
        assertEquals(Vector3.j, Vector3.i.cross(Vector3.k));
        assertEquals(Vector3.k, Vector3.i.cross(Vector3.j));
        assertEquals(Vector3.k, new Vector3(0, 0, 8).normalize());
        assertEquals(new Vector3(3 / 5.0, 4 / 5.0, 0), otherVector3.normalize());

    }

    @Test
    public void testTransforms(){
        TF tf = new TF();
        tf.put("Test", new Pose(new Point(1, 2, 3), new Quaternion(0, Vector3.k)));
        assertEquals(new Transform(new Vector3(-1, -2, -3), new Quaternion(0, Vector3.k)), tf.lookup("Test"));
        tf.put("Testing", "Test", new Pose(new Point(0, 0, 0), new Quaternion(Math.PI / 2, Vector3.k)));
        assertEquals(new Transform(new Vector3(0, 0, 0), new Quaternion(-Math.PI / 2, Vector3.k)), tf.lookup("Testing"));
        assertEquals(new Point(0, 0, 0), tf.transformToOrigin(new Point(-1, -2, -3), "Test"));
        assertEquals(new Point(2,-1, -3), tf.transform(new Point(-1, -2, -3), "Testing", "Test"));
        assertEquals(new Point(3, 1, 0), tf.transformToOrigin(new Point(-1, -2, -3), "Testing"));
        assertEquals(new Point(0, 0, 0), tf.transform(new Point(0, 0, 0), "Testing", "Test"));
    }

    @Test
    public void testPose() {
        Pose pose = new Pose(new Point(1, 2, 3), new Quaternion(90, Vector3.k));

        assertEquals(new Point(1, 2, 3), pose.position);
        assertEquals(new Quaternion(90, Vector3.k), pose.orientation);
    }

    @Test
    public void testQuaternion() {
        Quaternion quaternion = new Quaternion(90, Vector3.k);
        Quaternion quaternion1 = new Quaternion(0, Vector3.k);

        assertEquals(new Quaternion(0, Vector3.i), quaternion1);
        assertEquals(new Quaternion(0, Vector3.j), quaternion1);
        assertEquals(new Quaternion(-90, Vector3.k), quaternion.inverse());
        assertEquals(new Quaternion(0, Vector3.k), quaternion1.inverse());
        assertEquals(new Quaternion(90, Vector3.k), quaternion.multiply(quaternion1));
        // TODO: Add more test cases for quaternion operations
    }

}
