package ports.usecases.assessment.submitInstance;

public class SubmitInstanceResponse {
    public boolean SubmitSuccessful;

    public SubmitInstanceResponse() {}

    public SubmitInstanceResponse(boolean submitSuccessful) {
        this.SubmitSuccessful = submitSuccessful;
    }
}
