package com.vsd.virtualservicedog;

import android.content.Context;
import android.content.res.AssetManager;

import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


class PanicDetection {

    private static PanicDetection instance = null;
    private KNearestNeighbors knnclassifier;
    private String FILE_NAME = "data.txt";

    PanicDetection(Context context) {
        AssetManager am = context.getAssets();
        Dataset data = new DefaultDataset();
        try {
            InputStream is = am.open(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                double[] datapoint = new double[]{Double.parseDouble(tokens[0])};
                String label = tokens[1];
                Instance instance = new DenseInstance(datapoint,label);
                data.add(instance);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        knnclassifier = new KNearestNeighbors(1);
        knnclassifier.buildClassifier(data);
    }

    Object classifydata(Instance instance) {
        return knnclassifier.classify(instance);
    }

    public void addToTraining(double data, String label) {
        // TODO
    }

    public static PanicDetection getInstance(Context context) {
        if (instance == null) {
            instance = new PanicDetection(context);
        }
        return instance;
    }
}
