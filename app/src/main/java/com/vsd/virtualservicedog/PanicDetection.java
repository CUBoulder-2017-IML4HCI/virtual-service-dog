package com.vsd.virtualservicedog;

import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.DenseInstance;
import net.sf.javaml.core.Instance;


class PanicDetection {

    private KNearestNeighbors knnclassifier;
    PanicDetection() {
        knnclassifier = new KNearestNeighbors(5);
        Dataset data = new DefaultDataset();
        // TODO: write and read data from file
        double[] l1 = new double[]{100};
        double[] l2 = new double[]{120};
        double[] l3 = new double[]{90};
        double[] l4 = new double[]{60};
        double[] l5 = new double[]{70};
        double[] l6 = new double[]{80};
        Instance instance1 = new DenseInstance(l1,"yes");
        Instance instance2 = new DenseInstance(l2,"yes");
        Instance instance3 = new DenseInstance(l3,"yes");
        Instance instance4 = new DenseInstance(l4,"no");
        Instance instance5 = new DenseInstance(l5,"no");
        Instance instance6 = new DenseInstance(l6,"no");
        data.add(instance1);
        data.add(instance2);
        data.add(instance3);
        data.add(instance4);
        data.add(instance5);
        data.add(instance6);
        knnclassifier.buildClassifier(data);
    }

    public Object classifydata(Instance instance){
        return knnclassifier.classify(instance);
    }

    public void addToTraining(Object data, String label){
        // TODO
    }
}
