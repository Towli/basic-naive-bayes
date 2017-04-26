package com.towli;

/**
 * Created by Towli on 26/04/2017.
 */
public class DistributionSet {

    private double[] distributions;

    public DistributionSet(double[] distributions) {
        this.distributions = distributions;
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
