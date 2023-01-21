package ports.usecases.assessment.viewAssessment;

import ports.usecases.PathNotFoundError;

public interface ViewAssessmentInputBoundary {
    ViewAssessmentResponse execute(ViewAssessmentRequest request) throws PathNotFoundError;
}

