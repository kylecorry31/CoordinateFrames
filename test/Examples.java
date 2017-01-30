import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.kylecorry.tf.Point;
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

}
