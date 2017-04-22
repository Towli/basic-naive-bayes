package com.towli;

import weka.core.Instances;

import java.io.FileReader;

/**
 * Driver class for processing BasicNaiveBayes experiments
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Read in .arff files
        String filePath = "resources/crime_train.arff";
        Instances trainingInstances = readData(filePath);
        trainingInstances = initTrainingInstances(trainingInstances);

        System.out.println(trainingInstances.classAttribute().toString());

        // Build classifier
        BasicNaiveBayes naiveBayes = new BasicNaiveBayes();
        naiveBayes.buildClassifier(trainingInstances);
    }

    /**
     * Get Instances from specified data file
     * @return Instances
     */
    public static Instances readData(String filePath) {
        Instances instances = null;
        try {
            FileReader reader = new FileReader(filePath);
            instances = new Instances(reader);
        } catch(Exception e) {
            System.out.println("Exception caught: "+e);
        }
        return instances;
    }

    public static Instances initTrainingInstances(Instances trainingInstances) {
        trainingInstances.setClassIndex(trainingInstances.numAttributes()-1);
        return trainingInstances;
    }

}
