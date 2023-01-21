package ports.usecases.assessment.setWeightScheme;

public class SetSimpleWeightSchemeRequest {

    public String username; /*abstract to path class*/

    public String courseCode; /*abstract to path class*/

    public String assessmentTitle;
    public String numberOfInstances;

    public String weightOfEachInstance;

    public SetSimpleWeightSchemeRequest() {
    }

    public SetSimpleWeightSchemeRequest(String username, String courseCode, String assessmentTitle,
                                        String numberOfInstances, String weightOfEachInstance) {
        this.username = username;
        this.courseCode = courseCode;
        this.assessmentTitle = assessmentTitle;
        this.numberOfInstances = numberOfInstances;
        this.weightOfEachInstance = weightOfEachInstance;
    }

}
