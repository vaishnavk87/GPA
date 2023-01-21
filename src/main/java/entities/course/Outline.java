package entities.course;

import entities.assessment.Assessment;

import java.util.ArrayList;

public abstract class Outline {
    public abstract ArrayList<Assessment> getAssessments();

    public abstract ArrayList<String> getAssessmentsTitles();

    public abstract ArrayList<String> getAssessmentsSingularTitles();

    public abstract ArrayList<Integer> getAssessmentsNumberOfInstances();

    public abstract ArrayList<Double> getAssessmentsWeights();

    public abstract Assessment getAssessmentByTitle(String assessmentTitle);

    public abstract void addAssessment(Assessment assessment);

    public abstract void removeAssessment(Assessment assessment);

    public int getNumberOfAssessmentInstancesCompleted() {
        return getAssessments().stream()
                .mapToInt(Assessment::getNumberOfSubmittedInstances)
                .sum();
    }

    public double getPercentageCompleted() {
        return getAssessments().stream()
                .mapToDouble(Assessment::getMaxPossibleSubmittedWeight)
                .sum();
    }

    private double getTotalCommittedWeight() {
        return getAssessments().stream()
                .mapToDouble(Assessment::getMaxPossibleCommittedWeight)
                .sum();
    }

    private double getTotalHypotheticalWeight() {
        return getAssessments().stream()
                .mapToDouble(Assessment::getMaxPossibleHypotheticalWeight)
                .sum();
    }

    public double computeRunningGrade() {
        if (getTotalCommittedWeight() <= 0) {
            return 0;
        }
        return getAssessments().stream()
                .mapToDouble(Assessment::getActualCommittedWeight)
                .sum() / getTotalCommittedWeight();
    }

    public double computeHypotheticalGrade() {
        if (getTotalHypotheticalWeight() <= 0) {
            return 0;
        }
        return getAssessments().stream()
                .mapToDouble(Assessment::getActualHypotheticalWeight)
                .sum() / getTotalHypotheticalWeight();
    }

    public interface OutlineFactory {
        public Outline createOutline();
    }
}

