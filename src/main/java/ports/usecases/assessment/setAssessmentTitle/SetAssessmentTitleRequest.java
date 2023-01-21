package ports.usecases.assessment.setAssessmentTitle;

public class SetAssessmentTitleRequest {
    public String username; /*abstract to path class*/

    public String courseCode; /*abstract to path class*/

    public String currentAssessmentTitle; /*abstract to path class*/

    public String newAssessmentTitle;


    public SetAssessmentTitleRequest() {
    }

    public SetAssessmentTitleRequest(String username, String courseCode,
                                     String currentAssessmentTitle, String newAssessmentTitle) {
        this.username = username;
        this.courseCode = courseCode;
        this.currentAssessmentTitle = currentAssessmentTitle;
        this.newAssessmentTitle = newAssessmentTitle;
    }
}
