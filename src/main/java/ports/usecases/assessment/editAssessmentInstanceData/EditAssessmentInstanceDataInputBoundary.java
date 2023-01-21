package ports.usecases.assessment.editAssessmentInstanceData;

public interface EditAssessmentInstanceDataInputBoundary {
    /**
     * @param request the path to the AssessmentInstance and the title and deadline to set
     * @return the title and deadline that was set
     * @throws ports.usecases.PathNotFoundError                                         if the path to the AssessmentInstance does not exist
     * @throws EditAssessmentInstanceDataError if the new title or deadline is invalid
     */

    public EditAssessmentInstanceDataResponse execute(EditAssessmentInstanceDataRequest request)
            throws ports.usecases.PathNotFoundError, EditAssessmentInstanceDataInputBoundary.EditAssessmentInstanceDataError;

    public class EditAssessmentInstanceDataError extends Error {
        public EditAssessmentInstanceDataError(String message) {
            super(message);
        }
    }
}
