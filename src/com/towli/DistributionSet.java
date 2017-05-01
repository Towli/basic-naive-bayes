package com.towli;

/**
 * - Class to model a set of distributions
 * - Should be used (contained) by a DataAttribute object
 * - Should represent a set of distributions for an attribute given a single class value
 */
public class DistributionSet {

    private double[] distributions;

    public DistributionSet(double[] distributions) {
        this.distributions = distributions;
    }

    public double getDistributionByValue(int value) {
        return distributions[value];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < distributions.length; i++) {
            sb.append("Distribution " + i + ": ");
            sb.append(distributions[i]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
