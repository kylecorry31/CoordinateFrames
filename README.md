# Coordinate Frames and Transformations
This library allows for the transformation from one coordinate frame to another, allowing for objects to be specified relative to a specific frame, but then converted to another frame of reference.

The following code will create a TransformationMap to convert between coordinate frames.

```Java
TransformationMap tf = new TransformationMap();
tf.put("Camera", new Pose(new Point(1, 2, 3), Quaternion.zero));
tf.put("Arm", "Camera", new Pose(new Point(0, 0, 0), new Quaternion(Math.PI / 2, Vector3.k)));
Point fromOrigin = tf.transformToOrigin(new Point(-1, -2, -3), "Camera");
Point fromArm = tf.transform(new Point(-1, -2, -3), "Camera", "Arm");
```
