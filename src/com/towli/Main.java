package com.towli;

import weka.core.Instances;

import java.io.FileReader;

/**
 * Driver class for processing BasicNaiveBayes experiments
 */
public class Main {

    public static void main(String[] args) {
        // Read in .arff files
        String filePath = "data/crime_train.arff";
        Instances trainingInstances = readData(filePath);

        // getInstances() from .arffs
        // Create new BasicNaiveBayes classifier
        // Classify the test data
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

}
