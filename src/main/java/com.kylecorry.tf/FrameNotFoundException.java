package com.kylecorry.tf;

/**
 * This exception is thrown if the frame is not found in the TransformationMap
 */
public class FrameNotFoundException extends RuntimeException {
    public FrameNotFoundException(String frame) {
        super("Frame, " + frame + ", does not exist in the TransformationMap");
    }
}
