package ports.usecases.assessment.uncommitMark;

public class UncommitMarkRequest {
    public String username; /*abstract to path class*/

    public String courseCode; /*abstract to path class*/

    public String assessmentTitle; /*abstract to path class*/

    public int instanceNumber; /*abstract to path class*/

    public UncommitMarkRequest() {
    }

    public UncommitMarkRequest(String username, String courseCode, String assessmentTitle, int instanceNumber) {
        this.username = username;
        this.courseCode = courseCode;
        this.assessmentTitle = assessmentTitle;
        this.instanceNumber = instanceNumber;
    }
}
