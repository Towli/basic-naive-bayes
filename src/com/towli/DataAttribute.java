package com.towli;

/**
 * Created by Towli on 23/04/2017.
 */
public class DataAttribute {
    private int values;
    private int occurrences;
    private double probability;
    private int[] nominalCounts;

    public DataAttribute(int[] nominalCounts) {
        this.nominalCounts = nominalCounts;
    }

    public int getOccurrences() {
        return this.occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public int[] getNominalCounts() {
        return this.nominalCounts;
    }

    public void setNominalCounts(int[] nominalCounts) {
        this.nominalCounts = nominalCounts;
    }

    public double getProbability() {
        return this.probability;
    }
}
