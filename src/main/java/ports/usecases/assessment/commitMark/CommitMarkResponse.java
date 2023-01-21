package ports.usecases.assessment.commitMark;

public class CommitMarkResponse {

    public boolean commitSuccessful;

    public CommitMarkResponse() {
    }

    public CommitMarkResponse(boolean commitSuccessful) {
        this.commitSuccessful = commitSuccessful;
    }
}
