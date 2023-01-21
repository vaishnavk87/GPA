package ports.usecases.assessment.setAssessmentTitle;

public interface SetAssessmentTitleInputBoundary {
    /**
     * Set the title of an assessment
     *
     * @param request the path to the Assessment and its new title
     * @return the new title of the Assessment
     * @throws SetAssessmentTitleInputBoundary.SetAssessmentTitleError if the Assessment's title already exists
     * @throws ports.usecases.PathNotFoundError                        if the path to the Assessment does not exist
     */

    public SetAssessmentTitleResponse execute(SetAssessmentTitleRequest request)
            throws SetAssessmentTitleInputBoundary.SetAssessmentTitleError, ports.usecases.PathNotFoundError;

    public class SetAssessmentTitleError extends Error {
        public SetAssessmentTitleError(String message) {
            super(message);
        }
    }

}
