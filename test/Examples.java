import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.kylecorry.tf.Point;
import com.kylecorry.tf.Pose;
import com.kylecorry.tf.Quaternion;
import com.kylecorry.tf.Vector3;
import org.junit.Test;

/**
 * Created by Kylec on 1/29/2017.
 */
public class Examples {
    @Test
    public void testPoint(){
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
    }

    @Test
    public void testPose(){
        Pose pose = new Pose(new Point(1, 2, 3), new Quaternion(90, Vector3.k));

        assertEquals(new Point(1, 2, 3), pose.position);
        assertEquals(new Quaternion(90, Vector3.k), pose.orientation);
    }

    @Test
    public void testQuaternion(){
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
