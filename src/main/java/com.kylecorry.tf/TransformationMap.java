package com.kylecorry.tf;


import com.kylecorry.geometry.Point;
import com.kylecorry.geometry.Pose;
import com.kylecorry.geometry.Quaternion;
import com.kylecorry.geometry.Vector3;

import java.util.HashMap;
import java.util.List;

public class TransformationMap {
    private HashMap<String, TreeNode<Transform>> frames;

    public static final String ORIGIN = "origin";

    /**
     * Create a transformation map.
     */
    public TransformationMap() {
        Transform origin = new Transform(Vector3.zero, Quaternion.zero);
        TreeNode<Transform> root = new TreeNode<>(origin);
        frames = new HashMap<>();
        frames.put(ORIGIN, root);
    }

    /**
     * Lookup a frame in the transformation map.
     *
     * @param frame The frame to get.
     * @return The frame.
     */
    public Transform lookup(String frame) {
        return getNode(frame).getData();
    }


    /**
     * Get the TreeNode of the transform given the frame name. Throws a FrameNotFoundException if the frame is not in the tree.
     *
     * @param frame The name of the frame to fetch.
     * @return The TreeNode containing the transform for the given frame.
     */
    private TreeNode<Transform> getNode(String frame) {
        if (!frames.containsKey(frame)) {
            throw new FrameNotFoundException(frame);
        }
        return frames.get(frame);
    }

    /**
     * Transform a point from one frame to another.
     *
     * @param p         The point p.
     * @param fromFrame The frame which contains point p.
     * @param toFrame   The destination frame to move point p to.
     * @return A point representing point p, but in the destination frame.
     */
    public Point transform(Point p, String fromFrame, String toFrame) {
        TreeSearchEngine search = new TreeSearchEngine();
        TreeNode<Transform> ancestor = search.findLowestCommonAncestor(getNode(fromFrame), getNode(toFrame));
        TreeNode<Transform> current = getNode(fromFrame);
        Point currentPt = p;
        while (current != ancestor) {
            Transform c = current.getData();
            currentPt = applyTransformationTo(currentPt, c);
            current = current.getParent();

        }
        TreeNode<Transform> destination = getNode(toFrame);
        List<TreeNode<Transform>> ancestors = search.getAllAncestors(destination);
        int stopIndex = ancestors.indexOf(ancestor);
        for (int i = stopIndex - 1; i >= 0; i--) {
            Transform c = ancestors.get(i).getData();
            currentPt = applyTransformationBack(currentPt, c);
        }
        return currentPt;
    }

    private Point applyTransformationTo(Point p, Transform t) {
        Point rotation = t.rotation.inverse().rotate(p);
        return rotation.subtract(t.translation);
    }

    private Point applyTransformationBack(Point p, Transform t) {
        return t.rotation.rotate(p.subtract(t.translation.multiply(-1)));
    }

    /**
     * Transform a point from a frame to the origin.
     *
     * @param p         The point p.
     * @param fromFrame The frame which contains point p.
     * @return A point representing point p, but in the origin frame.
     */
    public Point transformToOrigin(Point p, String fromFrame) {
        return transform(p, fromFrame, ORIGIN);
    }

    /**
     * Put a frame in the map.
     *
     * @param frame    The name of the frame to add.
     * @param parent   The name of the parent frame.
     * @param location The location of the frame relative to the parent frame.
     */
    public void put(String frame, String parent, Pose location) {
        TreeNode<Transform> p = getNode(parent);
        Transform t = new Transform(new Vector3(location.position), location.orientation).inverse();
        TreeNode<Transform> child = new TreeNode<>(t);
        child.setParent(p);
        p.getChildren().add(child);
        frames.put(frame, child);
    }

    /**
     * Put a frame in the map.
     *
     * @param frame    The name of the frame to add.
     * @param location The location of the frame relative to the origin.
     */
    public void put(String frame, Pose location) {
        put(frame, ORIGIN, location);
    }
}
