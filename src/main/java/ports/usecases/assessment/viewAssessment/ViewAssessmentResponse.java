package ports.usecases.assessment.viewAssessment;

public class ViewAssessmentResponse {
    public String username;
    public String courseCode;
    public String assessmentTitle;
    public String[] assessmentInstanceTitles;
    public Double[] assessmentInstanceWeights;
    public Double[] assessmentInstanceMarks;
    public String semesterTitle;

    public ViewAssessmentResponse() {
    }

    public ViewAssessmentResponse(String username, String courseCode, String assessmentTitle,
                                  String[] assessmentInstanceTitles, Double[] assessmentInstanceWeights,
                                  Double[] assessmentInstanceMarks, String semesterTitle) {
        this.username = username;
        this.courseCode = courseCode;
        this.assessmentTitle = assessmentTitle;
        this.assessmentInstanceTitles = assessmentInstanceTitles;
        this.assessmentInstanceWeights = assessmentInstanceWeights;
        this.assessmentInstanceMarks = assessmentInstanceMarks;
        this.semesterTitle = semesterTitle;
    }

}
