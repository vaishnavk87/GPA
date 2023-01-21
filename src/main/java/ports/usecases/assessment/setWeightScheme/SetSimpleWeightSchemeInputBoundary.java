package ports.usecases.assessment.setWeightScheme;

import ports.usecases.assessment.addSimpleAssessment.AddSimpleAssessmentInputBoundary;
import ports.usecases.assessment.addSimpleAssessment.AddSimpleAssessmentRequest;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;
import ports.usecases.course.viewCourse.ViewCourseResponse;

public interface SetSimpleWeightSchemeInputBoundary {
    /**
     * Set a Simple WeightScheme for an Assessment
     *
     * @param request the path to the Assessment, the number of instances, and the weight of each instance
     * @return preliminary weightscheme data of assessment
     * @throws SetSimpleWeightSchemeInputBoundary.SetSimpleWeightSchemeError if the Assessment's weightscheme is invalid
     * @throws ports.usecases.PathNotFoundError                              if the path to the Assessment does not exist
     */

    ViewAssessmentResponse execute(SetSimpleWeightSchemeRequest request)
            throws AddSimpleAssessmentInputBoundary.AddAssessmentError, AddSimpleAssessmentInputBoundary.AddWeightSchemeError;

    public class SetSimpleWeightSchemeError extends Error {
        public SetSimpleWeightSchemeError(String message) {
            super(message);
        }
    }
}
