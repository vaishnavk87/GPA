package usecases.assessment.UncommitMark;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.assessment.AssessmentInstance;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.assessment.uncommitMark.UncommitMarkInputBoundary;
import ports.usecases.assessment.uncommitMark.UncommitMarkRequest;
import ports.usecases.assessment.uncommitMark.UncommitMarkResponse;

import java.util.ArrayList;

public class UncommitMarkUseCase implements UncommitMarkInputBoundary {

    private EntityGateway entityGateway;

    public UncommitMarkUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public UncommitMarkResponse execute(UncommitMarkRequest request)
            throws ports.usecases.PathNotFoundError, UncommitMarkInputBoundary.UncommitMarkError {

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


        if (!assessmentInstance.isCommitted()) {
            throw new UncommitMarkInputBoundary.UncommitMarkError("Assessment must be committed before it can be uncommitted");
        }

        assessmentInstance.setCommitted(false);
        entityGateway.saveAccount(account);

        return createResponse(assessmentInstance);
    }

    private UncommitMarkResponse createResponse(AssessmentInstance assessmentInstance) {
        UncommitMarkResponse response = new UncommitMarkResponse();
        response.uncommitSuccessful = true;
        return response;
    }
}
