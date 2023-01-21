package usecases.assessment.SetSimpleWeightScheme;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.course.Course;
import entities.weightScheme.SimpleWeight;
import entities.weightScheme.Weight;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.assessment.addSimpleAssessment.AddSimpleAssessmentInputBoundary;
import ports.usecases.assessment.setWeightScheme.SetSimpleWeightSchemeInputBoundary;
import ports.usecases.assessment.setWeightScheme.SetSimpleWeightSchemeRequest;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;
import ports.usecases.course.viewCourse.ViewCourseResponse;

import java.util.ArrayList;

public class SetSimpleWeightSchemeUseCase implements SetSimpleWeightSchemeInputBoundary {

    private EntityGateway entityGateway;

    private final Assessment.AssessmentFactory assessmentFactory;

    private Weight.WeightFactory weightFactory;
    private SimpleWeight.SimpleWeightFactory simpleWeightFactory;

    public SetSimpleWeightSchemeUseCase(EntityGateway entityGateway, Assessment.AssessmentFactory assessmentFactory, Weight.WeightFactory weightFactory,
                                        SimpleWeight.SimpleWeightFactory simpleWeightFactory) {
        this.entityGateway = entityGateway;
        this.assessmentFactory = assessmentFactory;
        this.weightFactory = weightFactory;
        this.simpleWeightFactory = simpleWeightFactory;
    }

    public ViewAssessmentResponse execute(SetSimpleWeightSchemeRequest request) throws
            SetSimpleWeightSchemeInputBoundary.SetSimpleWeightSchemeError {

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

        int numberOfInstances;
        try {
            numberOfInstances = Integer.parseInt(request.numberOfInstances);
        } catch (NumberFormatException ex) {
            throw new AddSimpleAssessmentInputBoundary.AddWeightSchemeError("Number of instances must be an integer");
        }

        double weightOfEachInstance;
        try {
            weightOfEachInstance = Double.parseDouble(request.weightOfEachInstance);
        } catch (NumberFormatException ex) {
            throw new AddSimpleAssessmentInputBoundary.AddWeightSchemeError("Weight of each instance must be a number between 0 and 1");
        }

        if (numberOfInstances < 1) {
            throw new SetSimpleWeightSchemeInputBoundary.SetSimpleWeightSchemeError("Number of instances must be greater than 0");
        }
        if (weightOfEachInstance < 0 || weightOfEachInstance == 0) {
            throw new SetSimpleWeightSchemeInputBoundary.SetSimpleWeightSchemeError("Weight of each instance must be greater than 0");
        }
        if (weightOfEachInstance > 1) {
            throw new SetSimpleWeightSchemeInputBoundary.SetSimpleWeightSchemeError("Weight of each instance must be less than or equal to 1");
        }
        if (numberOfInstances * weightOfEachInstance > 1) {
            throw new SetSimpleWeightSchemeInputBoundary.SetSimpleWeightSchemeError("Total weight of instances must be less than or equal to 1");
        }

        if (assessment.getNumberOfSubmittedInstances() > numberOfInstances) {
            throw new SetSimpleWeightSchemeInputBoundary.SetSimpleWeightSchemeError
                    ("New Weightscheme cannot have less assessment instances than the number of submitted assessment instances");
        }

        double totalWeightWithoutEditedAssessment = course.getOutline().getAssessmentsWeights().stream().mapToDouble(Double::doubleValue).sum() -
                assessment.getTotalWeight();

        if (numberOfInstances * weightOfEachInstance + totalWeightWithoutEditedAssessment > 1) {
            throw new AddSimpleAssessmentInputBoundary.AddWeightSchemeError("Adding this assessment would exceed the courses's total weight");
        }

        SimpleWeight weightScheme = simpleWeightFactory.createSimpleWeight(
                weightFactory.createWeight(numberOfInstances, weightOfEachInstance));

        course.getOutline().removeAssessment(assessment);

        Assessment resetAssessment = assessmentFactory.createAssessment(request.assessmentTitle, weightScheme);

        course.getOutline().addAssessment(resetAssessment);

        entityGateway.saveAccount(account);

        return createResponse(account, course, assessment);

    }

    /*private SetSimpleWeightSchemeResponse createResponse(Assessment assessment) {
        SetSimpleWeightSchemeResponse response = new SetSimpleWeightSchemeResponse();
        response.totalWeight = assessment.getWeightScheme().getTotalWeight();
        response.maximumNumberOfInstances = assessment.getWeightScheme().getNumberOfInstances();
        return response;
    }*/

    private ViewAssessmentResponse createResponse(Account account, Course course, Assessment assessment) {
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
            response.assessmentInstanceWeights[i] = assessment.getWeightScheme().getTotalWeight()/assessment.getInstances().size();
        }
        response.assessmentInstanceMarks = new Double[assessment.getInstances().size()];
        for (int i = 0; i < assessment.getInstances().size(); i++) {
            response.assessmentInstanceMarks[i] = assessment.getInstances().get(i).getMark();
        }
        return response;
    }
}
