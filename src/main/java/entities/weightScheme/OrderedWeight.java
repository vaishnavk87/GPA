package entities.weightScheme;

import java.util.Arrays;
import java.util.stream.Stream;
import java.util.stream.DoubleStream;

public abstract class OrderedWeight implements WeightScheme {
    public abstract Weight[] getOrderedWeights();
    public abstract void setOrderedWeights(Weight[] orderedWeights);

    @Override
    public double computeWeighted(double[] marks) {
        double[] paddedMarks = Arrays.copyOf(marks, getNumberOfInstances());
        double exactWeightEarned = 0;
        int numberOfMarksComputed = 0;
        for (Weight weight : getOrderedWeights()) {
            exactWeightEarned += DoubleStream.of(paddedMarks)
                    .sorted()
                    .skip(numberOfMarksComputed)
                    .limit(weight.getNumberOfInstances())
                    .map(mark -> mark * weight.getWeightOfEachInstance())
                    .sum()
            ;
            numberOfMarksComputed += weight.getNumberOfInstances();
        }
        return exactWeightEarned;
    }

    @Override
    public int getNumberOfInstances() {
        return Stream.of(getOrderedWeights())
                .mapToInt(Weight::getNumberOfInstances)
                .sum();
    }

    @Override
    public double getTotalWeight() {
        return Stream.of(getOrderedWeights())
                .mapToDouble(Weight::getTotalWeight)
                .sum();
    }

    public interface OrderedWeightFactory {
        public OrderedWeight createOrderedWeight(Weight[] orderedWeights);
    }
}
