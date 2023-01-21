package usecases.assessment;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.assessment.setAssessmentTitle.SetAssessmentTitleInputBoundary;
import ports.usecases.assessment.setAssessmentTitle.SetAssessmentTitleRequest;
import ports.usecases.assessment.setAssessmentTitle.SetAssessmentTitleResponse;

import java.util.ArrayList;

public class SetAssessmentTitleUseCase implements SetAssessmentTitleInputBoundary {

    private EntityGateway entityGateway;

    public SetAssessmentTitleUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public SetAssessmentTitleResponse execute(SetAssessmentTitleRequest request)
            throws SetAssessmentTitleError, ports.usecases.PathNotFoundError {

        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }

        Account account = entityGateway.loadAccount(request.username);
        Course course = account.getSemester().getCourseByCode(request.courseCode);

        if (course == null) {
            throw new PathNotFoundError();
        }

        ArrayList<String> assessmentTitles = course.getOutline().getAssessmentsTitles();
        if (!assessmentTitles.contains(request.currentAssessmentTitle)) {
            throw new PathNotFoundError();
        }
        if (assessmentTitles.contains(request.newAssessmentTitle)) {
            throw new SetAssessmentTitleError("Assessment title already exists");
        }
        Assessment assessment = course.getOutline().getAssessmentByTitle(request.currentAssessmentTitle);

        if (request.newAssessmentTitle.equals("")) {
            throw new SetAssessmentTitleError("Assessment title cannot be empty");
        }
        if (request.newAssessmentTitle.equals(request.currentAssessmentTitle)) {
            throw new SetAssessmentTitleError("Assessment title is the same as the current title");
        }
        if (request.newAssessmentTitle.contains("\n")) {
            throw new SetAssessmentTitleError("Assessment title cannot contain new lines");
        }
        if (request.newAssessmentTitle.length() > 30) {
            throw new SetAssessmentTitleError("Assessment title cannot be longer than 30 characters");
        }

        assessment.setTitle(request.newAssessmentTitle);
        entityGateway.saveAccount(account);
        return createResponse(assessment);
    }

    private SetAssessmentTitleResponse createResponse(Assessment assessment) {
        SetAssessmentTitleResponse response = new SetAssessmentTitleResponse();
        response.newAssessmentTitle = assessment.getTitle();
        return response;
    }

}
