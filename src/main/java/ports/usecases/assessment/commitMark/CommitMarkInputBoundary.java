package ports.usecases.assessment.commitMark;


public interface CommitMarkInputBoundary {
    /**
     * @param request the path to the AssessmentInstance to commit
     * @return the successful commit
     * @throws ports.usecases.PathNotFoundError        if the path to the AssessmentInstance does not exist
     * @throws CommitMarkInputBoundary.CommitMarkError if the AssessmentInstance can't be committed
     */

    public CommitMarkResponse execute(CommitMarkRequest request)
            throws ports.usecases.PathNotFoundError, CommitMarkInputBoundary.CommitMarkError;

    public class CommitMarkError extends Error {
        public CommitMarkError(String message) {
            super(message);
        }
    }
}
