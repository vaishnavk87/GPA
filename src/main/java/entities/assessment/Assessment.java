package entities.assessment;

import entities.weightScheme.WeightScheme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Assessment {
    public abstract String getTitle();

    public abstract String getSingularTitle();

    public abstract WeightScheme getWeightScheme();

    public abstract ArrayList<AssessmentInstance> getInstances();

    public abstract void setTitle(String title);

    public abstract void setWeightScheme(WeightScheme weightScheme);

    public abstract void addInstance(AssessmentInstance instance);

    public abstract void removeInstance(AssessmentInstance instance);

    public double getTotalWeight() {
        return getWeightScheme().getTotalWeight();
    }

    public int getTotalNumberOfInstances() {
        return getWeightScheme().getNumberOfInstances();
    }

    public int getCurrentNumberOfInstances() {
        return getInstances().size();
    }

    public int getNumberOfCommittedInstances() {
        return (int) getInstances().stream()
                .filter(AssessmentInstance::isCommitted)
                .count();
    }

    public int getNumberOfSubmittedInstances() {
        return (int) getInstances().stream()
                .filter(AssessmentInstance::isSubmitted)
                .count();
    }

    public double[] getCommittedMarks() {
        return getInstances().stream()
                .filter(AssessmentInstance::isCommitted)
                .mapToDouble(AssessmentInstance::getMark)
                .toArray();
    }

    public double[] getAllMarks() {
        return getInstances().stream()
                .filter(instance -> instance.getMark() != null)
                .mapToDouble(AssessmentInstance::getMark)
                .toArray();
    }

    private double getMaxWeight(int numberOfInstances) {
        double[] marksToWeigh = new double[getWeightScheme().getNumberOfInstances()];
        Arrays.fill(marksToWeigh, 0, numberOfInstances, 100);
        return getWeightScheme().computeWeighted(marksToWeigh) / 100;
    }

    private int getNumberOfMarkedInstances() {
        return getAllMarks().length;
    }


    public double getActualCommittedWeight() {
        return getWeightScheme().computeWeighted(getCommittedMarks());
    }

    public double getActualHypotheticalWeight() {
        return getWeightScheme().computeWeighted(getAllMarks());
    }

    public double getMaxPossibleCommittedWeight() {
        return getMaxWeight(getNumberOfCommittedInstances());
    }

    public double getMaxPossibleSubmittedWeight() {
        return getMaxWeight(getNumberOfSubmittedInstances());
    }

    public double getMaxPossibleHypotheticalWeight() {
        return getMaxWeight(getNumberOfMarkedInstances());
    }

    public String toSingular(String title) {
        if (title.equals("Quizzes") || title.equals("quizzes")){
            return "Quiz";
        }
        if (title.charAt(title.length()-1) == 's'){
            return title.substring(0, title.length()-1);
        }
        return title;
    }

    public interface AssessmentFactory {
        Assessment createAssessment(String title, WeightScheme weightScheme);

        Assessment createAssessment(String title, WeightScheme weightScheme, List<AssessmentInstance> instances);
    }
}
