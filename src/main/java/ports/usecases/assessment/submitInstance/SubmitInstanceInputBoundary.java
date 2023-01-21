package ports.usecases.assessment.submitInstance;

public interface SubmitInstanceInputBoundary {
    public SubmitInstanceResponse execute(SubmitInstanceRequest request)
            throws ports.usecases.PathNotFoundError, SubmitInstanceInputBoundary.SubmitError;

    public class SubmitError extends Error {
        public SubmitError(String message) {
            super(message);
        }
    }
}
