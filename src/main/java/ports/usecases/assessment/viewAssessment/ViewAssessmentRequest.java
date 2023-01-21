package ports.usecases.assessment.viewAssessment;

public class ViewAssessmentRequest {
    public String username;
    public String courseCode;
    public String assessmentTitle;
    public String semesterTitle;

    public ViewAssessmentRequest(String username, String courseCode, String assessmentTitle, String semesterTitle) {
        this.username = username;
        this.courseCode = courseCode;
        this.assessmentTitle = assessmentTitle;
        this.semesterTitle = semesterTitle;
    }
}

