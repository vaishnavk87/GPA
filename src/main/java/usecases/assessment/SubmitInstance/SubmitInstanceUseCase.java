package usecases.assessment.SubmitInstance;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.assessment.AssessmentInstance;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.assessment.submitInstance.SubmitInstanceInputBoundary;
import ports.usecases.assessment.submitInstance.SubmitInstanceRequest;
import ports.usecases.assessment.submitInstance.SubmitInstanceResponse;

import java.util.ArrayList;

public class SubmitInstanceUseCase {
    private EntityGateway entityGateway;

    public SubmitInstanceUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public SubmitInstanceResponse execute(SubmitInstanceRequest request)
            throws ports.usecases.PathNotFoundError {

        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }

        Account account = entityGateway.loadAccount(request.username);
        Course course = account.getSemester().getCourseByCode(request.courseCode);

        if (course == null) {
            throw new PathNotFoundError();
        }

        ArrayList<String> assessmentTitles = course.getOutline().getAssessmentsTitles();
        if (!assessmentTitles.contains(request.assessmentTitle)) {
            throw new PathNotFoundError();
        }
        Assessment assessment = course.getOutline().getAssessmentByTitle(request.assessmentTitle);

        if (request.instanceNumber >= assessment.getInstances().size()) {
            throw new PathNotFoundError();
        }

        AssessmentInstance assessmentInstance = assessment.getInstances().get(request.instanceNumber);


        if (assessmentInstance.isSubmitted()) {
            throw new SubmitInstanceInputBoundary.SubmitError("Assessment already submitted");
        }

        assessmentInstance.setSubmitted(true);
        entityGateway.saveAccount(account);
        return createResponse(assessmentInstance);
    }
    private SubmitInstanceResponse createResponse(AssessmentInstance assessmentInstance) {
        SubmitInstanceResponse response = new SubmitInstanceResponse(true);
        return response;
    }
}
