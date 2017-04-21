package com.towli;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;

/**
 * Created by Towli on 20/04/2017.
 */
public class BasicNaiveBayes implements Classifier {

    private int numClasses;
    private Instances instances;

    /**
     * Create the necessary data structures and count the required occurrences in the training data
     * @param instances
     * @throws Exception
     */
    @Override
    public void buildClassifier(Instances instances) throws Exception {
        this.numClasses = instances.numClasses();
        this.instances = instances;

        // loop over instances and calculate the distribution for each instance
        for (Instance instance : instances) {
            System.out.println(distributionForInstance(instance));
        }
    }

    @Override
    public double classifyInstance(Instance instance) throws Exception {
        // call distributionForInstance() and simply return the prediction as the class with the largest probability
        return 0;
    }

    @Override
    public double[] distributionForInstance(Instance instance) throws Exception {
        // work out the probabilities of class member-ship for a single instance
        return new double[0];
    }

    @Override
    public Capabilities getCapabilities() {
        return null;
    }
}
