package entities.weightScheme;

public abstract class Weight {
    public abstract int getNumberOfInstances();
    public abstract double getWeightOfEachInstance();

    public abstract void setNumberOfInstances(int numberOfInstances);
    public abstract void setWeightOfEachInstance(double weightOfEachInstance);

    public double getTotalWeight() {
        return getNumberOfInstances() * getWeightOfEachInstance();
    }

    public interface WeightFactory {
        Weight createWeight(int numberOfInstances, double weightOfEachInstance);
    }
}
