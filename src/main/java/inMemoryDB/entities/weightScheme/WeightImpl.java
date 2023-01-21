package inMemoryDB.entities.weightScheme;

import entities.weightScheme.Weight;

import java.io.Serializable;

public class WeightImpl extends Weight implements Serializable {
    private int numberOfInstances;
    private double weightOfEachInstance;

    /**
     * Create a new Weight object
     * @param numberOfInstances must be positive
     * @param weightOfEachInstance must be between 0 and 1
     */
    public WeightImpl(int numberOfInstances, double weightOfEachInstance) {
        this.numberOfInstances = numberOfInstances;
        this.weightOfEachInstance = weightOfEachInstance;
    }

    public int getNumberOfInstances() {
        return numberOfInstances;
    }

    public double getWeightOfEachInstance() {
        return weightOfEachInstance;
    }

    public void setNumberOfInstances(int numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public void setWeightOfEachInstance(double weightOfEachInstance) {
        this.weightOfEachInstance = weightOfEachInstance;
    }
}
