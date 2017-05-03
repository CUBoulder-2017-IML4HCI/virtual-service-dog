package com.vsd.virtualservicedog;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


class PanicDetection {

    private static PanicDetection instance = null;
    private KNearestNeighbors knnclassifier;
    private String FILE_NAME = "virtual-service-dog-data-test.txt";
    private Context context;

    PanicDetection(Context context) {
        this.context = context;
        checkTrainingDataFile();
        Dataset data = new DefaultDataset();
        try {
            FileInputStream is = context.openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = reader.readLine();
            while (line != null && !line.equals("")) {
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

    void addToTraining(double data, String label) {
        try {
            File file = new File(this.context.getFilesDir(), FILE_NAME);
            String text = String.valueOf(data) + "," + label + "\n";
            BufferedWriter buf = new BufferedWriter(new FileWriter(file, true));
            buf.append(text);
            buf.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkTrainingDataFile(){
        File file = new File(this.context.getFilesDir(), FILE_NAME);
        //if the file does not exits, create the file and put default training data
        if(!file.exists()){
            try {
                file.createNewFile();
                BufferedWriter buf = new BufferedWriter(new FileWriter(file, true));
                String text = "60,no\n" +
                            "75,yes\n" +
                            "80,yes\n" +
                            "95,yes\n" +
                            "65,no\n" +
                            "55,no\n" +
                            "70,no\n";
                buf.append(text);
                buf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static PanicDetection getInstance(Context context) {
        if (instance == null) {
            instance = new PanicDetection(context);
        }
        return new PanicDetection(context);
    }
}
