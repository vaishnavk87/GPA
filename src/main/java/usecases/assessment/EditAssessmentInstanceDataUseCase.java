package usecases.assessment;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.assessment.AssessmentInstance;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.assessment.editAssessmentInstanceData.EditAssessmentInstanceDataInputBoundary;
import ports.usecases.assessment.editAssessmentInstanceData.EditAssessmentInstanceDataRequest;
import ports.usecases.assessment.editAssessmentInstanceData.EditAssessmentInstanceDataResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class EditAssessmentInstanceDataUseCase implements EditAssessmentInstanceDataInputBoundary {

    private EntityGateway entityGateway;

    public EditAssessmentInstanceDataUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public EditAssessmentInstanceDataResponse execute(EditAssessmentInstanceDataRequest request)
            throws ports.usecases.PathNotFoundError, EditAssessmentInstanceDataInputBoundary.EditAssessmentInstanceDataError {

        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }

        Account account = entityGateway.loadAccount(request.username);
        Course course = account.getSemester().getCourseByCode(request.courseCode);

        if (course == null) {
            throw new PathNotFoundError();
        }

        ArrayList<String> assessmentTitles = course.getOutline().getAssessmentsTitles();

        ArrayList<String> assessmentSingularTitles = course.getOutline().getAssessmentsSingularTitles();

        if (!assessmentTitles.contains(request.assessmentTitle)) {
            throw new PathNotFoundError();
        }
        Assessment assessment = course.getOutline().getAssessmentByTitle(request.assessmentTitle);

        if (request.instanceNumber > assessment.getInstances().size()) {
            throw new PathNotFoundError();
        }

        AssessmentInstance assessmentInstance = assessment.getInstances().get(request.instanceNumber);

        String oldTitle = assessmentInstance.getTitle();
        LocalDateTime oldDeadline = assessmentInstance.getDeadline();

        if (request.newTitle.length() > 30) {
            throw new EditAssessmentInstanceDataInputBoundary.EditAssessmentInstanceDataError("Title must be less than 30 characters");
        }

        if (assessmentSingularTitles.contains(request.newTitle)) {
            throw new EditAssessmentInstanceDataInputBoundary.EditAssessmentInstanceDataError("Title must not mimic another assessment");
        }

        if (request.newTitle.equals(oldTitle)) {
            throw new EditAssessmentInstanceDataInputBoundary.EditAssessmentInstanceDataError("Title must be different");
        }

        if (request.newTitle.equals("")) {
            throw new EditAssessmentInstanceDataInputBoundary.EditAssessmentInstanceDataError("Title must not be empty");
        }


        if (request.newDeadline.isEqual(oldDeadline)) {
            throw new EditAssessmentInstanceDataInputBoundary.EditAssessmentInstanceDataError("Deadline must be different");
        }

        if (request.newDeadline.isBefore(LocalDateTime.now())) {
            throw new EditAssessmentInstanceDataInputBoundary.EditAssessmentInstanceDataError("Deadline must be in the future");
        }


        assessmentInstance.setTitle(request.newTitle);
        assessmentInstance.setDeadline(request.newDeadline);

        entityGateway.saveAccount(account);

        return createResponse(assessmentInstance);
    }

    private EditAssessmentInstanceDataResponse createResponse(AssessmentInstance assessmentInstance) {
        EditAssessmentInstanceDataResponse response = new EditAssessmentInstanceDataResponse();
        response.newTitle = assessmentInstance.getTitle();
        response.newDeadline = assessmentInstance.getDeadline();
        return response;
    }
}
