package com.vsd.virtualservicedog;

import org.tensorflow.contrib.android.TensorFlowInferenceInterface;
import org.tensorflow.contrib.android.RunStats;
import java.io.IOException;


public class TFPanicDetection implements Classifier{
    //TODO: exact values TBD
    private static final int MAX_RESULTS = 3;
    private static final float THRESHOLD = 0.1f;

    private TensorFlowInferenceInterface inferenceInterface;
    /**
     * Initializes a native TensorFlow session for classifying user status.
     * @throws IOException
     */
    public static Classifier create(

    )
    throws IOException {
        TFPanicDetection pd = new TFPanicDetection();
        return pd;
    }
}
