package com.towli;

import weka.core.Attribute;

/**
 * Class to model an Attribute for use in a Classifier
 * - Contains an array of DistributionSets
 * - Idea is that a DistributionSet models a set of distributions for a given class
 *      so the DistributionSet array will have a size equal to the amount of class values
 */
public class DataAttribute {

    private Attribute attribute;
    private DistributionSet[] distributionSets;

    public DataAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public void initDistributionSets(int size) {
        distributionSets = new DistributionSet[size];
    }

    public DistributionSet[] getDistributionSets() {
        return this.distributionSets;
    }

    public void assignDistributionSet(int index, DistributionSet distributions) {
        distributionSets[index] = distributions;
    }

    public Attribute getAttribute() {
        return this.attribute;
    }

}
