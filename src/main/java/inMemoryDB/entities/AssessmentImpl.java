package inMemoryDB.entities;


import entities.assessment.Assessment;
import entities.assessment.AssessmentInstance;
import entities.weightScheme.WeightScheme;

import java.io.Serializable;
import java.util.ArrayList;

public class AssessmentImpl extends Assessment implements Serializable {
    private String title;
    private WeightScheme weightScheme;
    private final ArrayList<AssessmentInstance> instances = new ArrayList<>();

    public AssessmentImpl(String title, WeightScheme weightScheme) {
        this.title = title;
        this.weightScheme = weightScheme;

        for (int i = 0, j = 1; i < this.getTotalNumberOfInstances(); i++, j++) {
            this.instances.add(new AssessmentInstanceImpl(getSingularTitle() + " #" + j));
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getSingularTitle() {
        return toSingular(this.title);
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public WeightScheme getWeightScheme() {
        return weightScheme;
    }

    // TODO: ensure setting weightscheme adjusts instance list
    @Override
    public void setWeightScheme(WeightScheme weightScheme) {
        this.weightScheme = weightScheme;
    }

    @Override
    public ArrayList<AssessmentInstance> getInstances() {
        return instances;
    }

   /*not necessary anymore if constructor automatically creates instance list */
    @Override
    public void addInstance(AssessmentInstance instance) {
        if (getCurrentNumberOfInstances() < getTotalNumberOfInstances()) {
            // TODO: throw appropriate error
            throw new Error("?");
        }
        if (!instances.contains(instance)) {
            instances.add(instance);
        }
    }

    /*not necessary anymore if constructor automatically creates instance list */
    @Override
    public void removeInstance(AssessmentInstance instance) {
        instances.remove(instance);
    }
}