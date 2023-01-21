package ports.usecases.assessment.uncommitMark;


public interface UncommitMarkInputBoundary {
    /**
     * @param request the path to the AssessmentInstance to uncommit
     * @return the successful uncommit
     * @throws ports.usecases.PathNotFoundError            if the path to the AssessmentInstance does not exist
     * @throws UncommitMarkInputBoundary.UncommitMarkError if the AssessmentInstance can't be uncommitted
     */

    public UncommitMarkResponse execute(UncommitMarkRequest request)
            throws ports.usecases.PathNotFoundError, UncommitMarkInputBoundary.UncommitMarkError;

    public class UncommitMarkError extends Error {
        public UncommitMarkError(String message) {
            super(message);
        }
    }
}
