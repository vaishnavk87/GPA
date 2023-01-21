package inMemoryDB.entities;

import entities.assessment.Assessment;
import entities.course.Outline;

import java.io.Serializable;
import java.util.ArrayList;

public class OutlineImpl extends Outline implements Serializable {

    private final ArrayList<Assessment> assessments = new ArrayList<>();

    @Override
    public ArrayList<Assessment> getAssessments() {
        return assessments;
    }

    @Override
    public ArrayList<String> getAssessmentsTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (Assessment assessment : assessments) {
            titles.add(assessment.getTitle());
        }
        return titles;
    }

    @Override
    public ArrayList<String> getAssessmentsSingularTitles() {
        ArrayList<String> singularTitles = new ArrayList<>();
        for (Assessment assessment : assessments) {
            singularTitles.add(assessment.getSingularTitle());
        }
        return singularTitles;
    }

    @Override
    public ArrayList<Integer> getAssessmentsNumberOfInstances() {
        ArrayList<Integer> numberOfInstances = new ArrayList<>();
        for (Assessment assessment : assessments) {
            numberOfInstances.add(assessment.getTotalNumberOfInstances());
        }
        return numberOfInstances;
    }

    @Override
    public ArrayList<Double> getAssessmentsWeights() {
        ArrayList<Double> weights = new ArrayList<>();
        for (Assessment assessment : assessments) {
            weights.add(assessment.getTotalWeight());
        }
        return weights;
    }

    @Override
    public Assessment getAssessmentByTitle(String assessmentTitle) {
        for (Assessment assessment : assessments) {
            if (assessment.getTitle().equals(assessmentTitle)) {
                return assessment;
            }
        }
        return null;
    }

    // TODO: should assessments be added if their weight sums over 100%?
    @Override
    public void addAssessment(Assessment assessment) {
        if (!assessments.contains(assessment)) {
            assessments.add(assessment);
        }
    }

    @Override
    public void removeAssessment(Assessment assessment) {
        assessments.remove(assessment);
    }
}
