package ports.usecases.assessment.addSimpleAssessment;

import ports.usecases.course.viewCourse.ViewCourseResponse;

public interface AddSimpleAssessmentInputBoundary {
    /**
     * Add an Assessment
     *
     * @param request the title and weightscheme of the Assessment to use
     * @return preliminary assessment data
     * @throws AddSimpleAssessmentInputBoundary.AddAssessmentError   if the Assessment's title already exists
     * @throws AddSimpleAssessmentInputBoundary.AddWeightSchemeError if the Assessment's weightscheme is invalid
     * @throws ports.usecases.PathNotFoundError                      if the path to the Assessment's course does not exist
     */
    ViewCourseResponse execute(AddSimpleAssessmentRequest request)
            throws AddSimpleAssessmentInputBoundary.AddAssessmentError, AddSimpleAssessmentInputBoundary.AddWeightSchemeError;


    class AddAssessmentError extends Error {
        public AddAssessmentError() {
            super("Assessment already exists");
        }
    }

    class AddWeightSchemeError extends Error {
        public AddWeightSchemeError(String message) {
            super(message);
        }
    }
}
