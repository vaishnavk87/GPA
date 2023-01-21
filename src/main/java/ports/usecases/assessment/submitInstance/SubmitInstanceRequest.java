package ports.usecases.assessment.submitInstance;

public class SubmitInstanceRequest {
    public String username; /*abstract to path class*/

    public String courseCode; /*abstract to path class*/

    public String assessmentTitle; /*abstract to path class*/

    public int instanceNumber; /*abstract to path class*/

    public SubmitInstanceRequest() {}

    public SubmitInstanceRequest(String username, String courseCode, String assessmentTitle, int instanceNumber) {
        this.username = username;
        this.courseCode = courseCode;
        this.assessmentTitle = assessmentTitle;
        this.instanceNumber = instanceNumber;
    }
}
