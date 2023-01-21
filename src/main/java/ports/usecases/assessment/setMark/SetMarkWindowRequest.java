package ports.usecases.assessment.setMark;

public class SetMarkWindowRequest {
    public String username; /*abstract to path class*/

    public String courseCode; /*abstract to path class*/

    public String assessmentTitle; /*abstract to path class*/

    public String instanceName;

    public int instanceNumber; /*abstract to path class*/

    public SetMarkWindowRequest() {}

    public SetMarkWindowRequest(String username, String courseCode, String assessmentTitle, String instanceName, int instanceNumber) {
        this.username = username;
        this.courseCode = courseCode;
        this.assessmentTitle = assessmentTitle;
        this.instanceName = instanceName;
        this.instanceNumber = instanceNumber;
    }
}
