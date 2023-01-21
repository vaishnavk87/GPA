package inMemoryDB.entities.weightScheme;

import entities.weightScheme.OrderedWeight;
import entities.weightScheme.Weight;

import java.io.Serializable;

public class OrderedWeightImpl extends OrderedWeight implements Serializable {
    private Weight[] orderedWeights;

    /**
     * Create new OrderedWeight, with (orderedWeights) being an array of Weight objects
     * @param orderedWeights must be sorted from lowest impact to highest impact.
     */
    public OrderedWeightImpl(Weight[] orderedWeights) {
        this.orderedWeights = orderedWeights;
    }

    public Weight[] getOrderedWeights() {
        return orderedWeights;
    }

    public void setOrderedWeights(Weight[] orderedWeights) {
        this.orderedWeights = orderedWeights;
    }
}
