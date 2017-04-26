package com.towli;

import weka.classifiers.Classifier;
import weka.core.Attribute;
import weka.core.Capabilities;
import weka.core.Instance;
import weka.core.Instances;
import java.util.ArrayList;

/**
 * Created by Towli on 20/04/2017.
 */
public class BasicNaiveBayes implements Classifier {

    private int numClasses;
    private Instances instances;
    private ArrayList<DataAttribute> attributes;
    private ClassAttribute classAttribute;

    /**
     * Create the necessary data structures and count the required occurrences in the training data
     * @param instances
     * @throws Exception
     */
    @Override
    public void buildClassifier(Instances instances) throws Exception {
        this.numClasses = instances.numClasses();
        this.instances = instances;
        createDataAttributes();
        createClassAttributes();
    }

    @Override
    public double classifyInstance(Instance instance) throws Exception {
        distributionForInstance(instance);
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

    /**
     * Create DataAttribute objects from the training data set
     * -> Ignores the ClassAttribute
     */
    private void createDataAttributes() {
        for (int i = 0; i < this.instances.numAttributes(); ++i)
            while (i != instances.classIndex())
                this.attributes.add(new DataAttribute(instances.attribute(i)));
    }

    /**
     * Create ClassAttribute objects from the training data set
     * -> currently assumes only a single class attribute
     */
    private void createClassAttributes() {
        this.classAttribute = new ClassAttribute(instances.classAttribute());
    }

    private void calculateConditionalDistributions(Attribute attribute) {

    }
}
