package entities.weightScheme;

import entities.MockEntityFactory;
import entities.weightScheme.OrderedWeight;
import entities.weightScheme.SimpleWeight;
import entities.weightScheme.Weight;
import entities.weightScheme.WeightScheme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class WeightSchemeTest {
    MockEntityFactory mockEntityFactory = new MockEntityFactory();

    private double[] marks = {100, 95, 80, 75.2, 64, 0, 99.5};

    private Weight[] weights = {
            mockEntityFactory.createWeight(1, 0.05),
            mockEntityFactory.createWeight(2, 0.30),
            mockEntityFactory.createWeight(4, 0.025)
    };

    @Test
    public void SimpleWeightComputation() {
        Weight weight = mockEntityFactory.createWeight(marks.length, 0.05);
        WeightScheme simpleWeight = mockEntityFactory.createSimpleWeight(weight);
        Assertions.assertEquals(25.68, simpleWeight.computeWeighted(marks), 0.01);
    }

    @Test
    public void SimpleWeightRunningComputation() {
        double[] test_marks = {75, 50};
        Weight weight = mockEntityFactory.createWeight(3, 0.10);
        WeightScheme simpleWeight = mockEntityFactory.createSimpleWeight(weight);
        Assertions.assertEquals(12.5, simpleWeight.computeWeighted(test_marks), 0.001);
    }

    @Test
    public void OrderedWeightNumberOfInstances() {
        Assertions.assertEquals(mockEntityFactory.createOrderedWeight(weights).getNumberOfInstances(), 7);
    }

    @Test
    public void OrderedWeightTotalWeight() {
        Assertions.assertEquals(mockEntityFactory.createOrderedWeight(weights).getTotalWeight(), 0.75);
    }

    @Test
    public void OrderedWeightComputation() {
        Assertions.assertEquals(51.1225, mockEntityFactory.createOrderedWeight(weights).computeWeighted(marks), 0.001);
    }

    @Test
    public void OrderedWeightRunningComputation() {
        double[] test_marks = {
                50, 100, 80
        };
        // Top Mark: 20%, Next 3 highest: each 10%, Lowest mark: 5%. Total marks: 5. Total Weight: 55%.
        Weight[] test_weights = {
                mockEntityFactory.createWeight(1, 0.05),
                mockEntityFactory.createWeight(3, 0.1),
                mockEntityFactory.createWeight(1, 0.2),
        };
        Assertions.assertEquals(
                33,
                mockEntityFactory.createOrderedWeight(test_weights).computeWeighted(test_marks),
                0.001);

    }
}
