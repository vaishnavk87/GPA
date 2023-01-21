package usecases.assessment.SetMark;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.assessment.AssessmentInstance;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.assessment.setMark.SetMarkInputBoundary;
import ports.usecases.assessment.setMark.SetMarkRequest;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;

import java.util.ArrayList;

public class SetMarkUseCase implements SetMarkInputBoundary {

    private final EntityGateway entityGateway;

    public SetMarkUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public ViewAssessmentResponse execute(SetMarkRequest request)
            throws ports.usecases.PathNotFoundError, SetMarkInputBoundary.SetMarkError {

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

        if (request.instanceNumber > assessment.getInstances().size()) {
            throw new PathNotFoundError();
        }

        AssessmentInstance assessmentInstance = assessment.getInstances().get(request.instanceNumber);

        double markValue;
        try {
            markValue = Double.parseDouble(request.mark);
        } catch (NumberFormatException e) {
            throw new SetMarkError("Mark must be a number");
        }

        if (markValue < 0 || markValue > 100) {
            throw new SetMarkInputBoundary.SetMarkError("Mark must be between 0 and 100");
        }

        if (assessmentInstance.isCommitted()) {
            throw new SetMarkInputBoundary.SetMarkError("Cannot change mark of committed assessment");
        }
        assessmentInstance.setMark(markValue);
        entityGateway.saveAccount(account);
        return createResponse(assessment, account, course);
    }

    private ViewAssessmentResponse createResponse(Assessment assessment, Account account, Course course) {
        ViewAssessmentResponse response = new ViewAssessmentResponse();
        response.username = account.getUsername();
        response.courseCode = course.getCourseCode();
        response.assessmentTitle = assessment.getTitle();
        response.assessmentInstanceTitles = new String[assessment.getInstances().size()];
        for (int i = 0; i < assessment.getInstances().size(); i++) {
            response.assessmentInstanceTitles[i] = assessment.getInstances().get(i).getTitle();
        }
        response.assessmentInstanceWeights = new Double[assessment.getInstances().size()];
        for (int i = 0; i < assessment.getInstances().size(); i++) {
            response.assessmentInstanceWeights[i] = assessment.getWeightScheme().getTotalWeight() / assessment.getInstances().size();
        }
        response.assessmentInstanceMarks = new Double[assessment.getInstances().size()];
        for (int i = 0; i < assessment.getInstances().size(); i++) {
            response.assessmentInstanceMarks[i] = assessment.getInstances().get(i).getMark();
        }
        return response;
    }
}


