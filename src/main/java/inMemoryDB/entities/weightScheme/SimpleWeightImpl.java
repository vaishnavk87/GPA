package inMemoryDB.entities.weightScheme;

import entities.weightScheme.SimpleWeight;
import entities.weightScheme.Weight;

import java.io.Serializable;
import java.util.stream.DoubleStream;

public class SimpleWeightImpl extends SimpleWeight implements Serializable {
    private Weight weight;

    public SimpleWeightImpl(Weight weight) {
        this.weight = weight;
    }

    @Override
    public Weight getWeight() {
        return weight;
    }

    @Override
    public void setWeight(Weight weight) {
        this.weight = weight;
    }
}
