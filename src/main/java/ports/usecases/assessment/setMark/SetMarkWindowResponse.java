package ports.usecases.assessment.setMark;

public class SetMarkWindowResponse {



    public String username;
    public String courseCode;
    public String assessmentTitle;
    public String instanceName;
    public int instanceNumber;

    public SetMarkWindowResponse() {}

    public SetMarkWindowResponse(String instanceName, int instanceNumber) {

        this.username = username;

        this.courseCode = courseCode;

        this.assessmentTitle = assessmentTitle;

        this.instanceName = instanceName;

        this.instanceNumber = instanceNumber;
    }
}
