package ports.usecases.assessment.addSimpleAssessment;

public class AddSimpleAssessmentResponse_DEPRECATED {

    public String assessmentTitle;

    public double totalWeight;

    public int maximumNumberOfInstances;

    public AddSimpleAssessmentResponse_DEPRECATED() {
    }

    public AddSimpleAssessmentResponse_DEPRECATED(String assessmentTitle, double totalWeight, int maximumNumberOfInstances) {
        this.assessmentTitle = assessmentTitle;
        this.totalWeight = totalWeight;
        this.maximumNumberOfInstances = maximumNumberOfInstances;
    }

}
