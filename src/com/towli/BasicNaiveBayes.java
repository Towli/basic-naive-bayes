package com.towli;

import weka.classifiers.Classifier;
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
        this.attributes = new ArrayList<>();

        createDataAttributes();
        createClassAttributes();

        for (DataAttribute attribute : attributes)
            calculateDistributions(attribute);

        for (DataAttribute attribute : attributes)
            for (DistributionSet set : attribute.getDistributionSets())
                System.out.println(set);
    }

    @Override
    public double classifyInstance(Instance instance) throws Exception {
        return distributionForInstance(instance)[1];
    }

    /**
     * Work out the probabilities of class member-ship for a single instance
     * @param instance
     * @return
     * @throws Exception
     */
    @Override
    public double[] distributionForInstance(Instance instance) throws Exception {
        int classValue = (int)instance.classValue();
        int attributeValue;
        double distribution = 1.0d;
        System.out.println("Distribution for instance...");
        for (DataAttribute attribute : attributes) {
            attributeValue = (int)instance.value(attribute.getAttribute());
            distribution *= attribute.getDistributionSets()[classValue].getDistributionByValue(attributeValue);
        }
        distribution *= classAttribute.getMarginal(classValue, instances.numInstances());

        double[] dist = {0, distribution};
        return dist;
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
            if (i != instances.classIndex())
                this.attributes.add(new DataAttribute(instances.attribute(i)));
    }

    /**
     * Create ClassAttribute objects from the training data set
     * -> currently assumes only a single class attribute
     */
    private void createClassAttributes() {
        int[] nominalCounts = instances.attributeStats(instances.classIndex()).nominalCounts;
        this.classAttribute = new ClassAttribute(instances.classAttribute());
        this.classAttribute.setNominalCounts(nominalCounts);
    }

    /**
     * Calculates the distributions for an attribute
     * -> iterates for each value of the dataset's Class Attribute
     * @param attribute
     */
    private void calculateDistributions(DataAttribute attribute) {
        attribute.initDistributionSets(numClasses); //maybe reference classAttribute.numValues instead
        DistributionSet newDistributionSet;
        for (int i = 0; i < numClasses; ++i) {
            newDistributionSet = calculateConditionals(i, attribute);
            attribute.assignDistributionSet(i, newDistributionSet);
        }
    }

    /**
     * Iterate over the the set of training instances, counting the occurrences of each value of
     * the provided for each value of the Class attribute.
     * @param classValueIndex
     * @param attribute
     * @return
     */
    private DistributionSet calculateConditionals(int classValueIndex, DataAttribute attribute) {
        int counter = 0;
        int attributeValues = attribute.getAttribute().numValues();
        int classValueOccurrences = classAttribute.getNominalCounts()[classValueIndex];
        double[] conditionalDistributions = new double[attributeValues];

        for (int i = 0; i < attributeValues; ++i) {
            for (int j = 0; j < instances.numInstances(); ++j) {
                if (instances.instance(j).stringValue(attribute.getAttribute()) == attribute.getAttribute().value(i) &&
                        String.valueOf((int) instances.instance(j).classValue()).equalsIgnoreCase(classAttribute.getValueByIndex(classValueIndex)))
                    counter++;
            }
            conditionalDistributions[i] = (double)counter / (double)classValueOccurrences;
            counter = 0;
        }
        return new DistributionSet(conditionalDistributions);
    }

}
