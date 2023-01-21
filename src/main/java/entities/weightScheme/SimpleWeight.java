package entities.weightScheme;

import java.util.stream.DoubleStream;

public abstract class SimpleWeight implements WeightScheme {
    public abstract Weight getWeight();
    public abstract void setWeight(Weight weight);

    @Override
    public double computeWeighted(double[] marks) {
        return DoubleStream.of(marks)
                .map(mark -> mark * getWeight().getWeightOfEachInstance())
                .sum();
    }

    @Override
    public double getTotalWeight() {
        return getWeight().getTotalWeight();
    }

    @Override
    public int getNumberOfInstances() {
        return getWeight().getNumberOfInstances();
    }

    public interface SimpleWeightFactory {
        SimpleWeight createSimpleWeight(Weight weight);
    }
}
