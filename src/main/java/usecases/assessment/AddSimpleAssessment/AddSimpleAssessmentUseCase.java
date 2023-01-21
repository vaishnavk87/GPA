package usecases.assessment.AddSimpleAssessment;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.course.Course;
import entities.weightScheme.SimpleWeight;
import entities.weightScheme.Weight;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.assessment.addSimpleAssessment.AddSimpleAssessmentInputBoundary;
import ports.usecases.assessment.addSimpleAssessment.AddSimpleAssessmentRequest;
import ports.usecases.course.viewCourse.ViewCourseResponse;

public class AddSimpleAssessmentUseCase implements AddSimpleAssessmentInputBoundary {

    private final EntityGateway entityGateway;
    private final Assessment.AssessmentFactory assessmentFactory;
    private Weight.WeightFactory weightFactory;
    private SimpleWeight.SimpleWeightFactory simpleWeightFactory;

    public AddSimpleAssessmentUseCase(EntityGateway entityGateway,
                                      Assessment.AssessmentFactory assessmentFactory,
                                      Weight.WeightFactory weightFactory,
                                      SimpleWeight.SimpleWeightFactory simpleWeightFactory) {
        this.entityGateway = entityGateway;
        this.assessmentFactory = assessmentFactory;
        this.weightFactory = weightFactory;
        this.simpleWeightFactory = simpleWeightFactory;
    }

    public ViewCourseResponse execute(AddSimpleAssessmentRequest request)
            throws AddAssessmentError, AddWeightSchemeError, PathNotFoundError {

        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }

        Account account = entityGateway.loadAccount(request.username);
        Course course = account.getSemester().getCourseByCode(request.courseCode);

        if (course == null) {
            throw new PathNotFoundError("Course is null");
        }

        if (course.getOutline().getAssessmentsTitles().contains(request.assessmentTitle)) {
            throw new AddAssessmentError();
        }

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
            throw new AddSimpleAssessmentInputBoundary.AddWeightSchemeError("Number of instances must be greater than 0");
        }
        if (weightOfEachInstance < 0 || weightOfEachInstance == 0) {
            throw new AddSimpleAssessmentInputBoundary.AddWeightSchemeError("Weight of each instance must be greater than 0");
        }
        if (weightOfEachInstance > 1) {
            throw new AddSimpleAssessmentInputBoundary.AddWeightSchemeError("Weight of each instance must be less than or equal to 1");
        }
        if (numberOfInstances * weightOfEachInstance > 1) {
            throw new AddSimpleAssessmentInputBoundary.AddWeightSchemeError("Total weight of instances must be less than or equal to 1");
        }

        double totalWeight = course.getOutline().getAssessmentsWeights().stream().mapToDouble(Double::doubleValue).sum();

        if (numberOfInstances * weightOfEachInstance + totalWeight > 1) {
            throw new AddSimpleAssessmentInputBoundary.AddWeightSchemeError("Adding this assessment would exceed the courses's total weight");
        }

        SimpleWeight weightScheme = simpleWeightFactory.createSimpleWeight(
                weightFactory.createWeight(numberOfInstances, weightOfEachInstance)
        );

        Assessment assessment = assessmentFactory.createAssessment(request.assessmentTitle, weightScheme);

        course.getOutline().addAssessment(assessment);
        entityGateway.saveAccount(account);
        return createResponse(course, account);

    }

    private ViewCourseResponse createResponse(Course course, Account account) {
        ViewCourseResponse response = new ViewCourseResponse();
        response.username = account.getUsername();
        response.courseCode = course.getCourseCode();
        response.courseTitle = course.getCourseName();
        response.credit = Float.toString(course.getCredit());
        response.assessmentTitles = new String[course.getOutline().getAssessmentsTitles().size()];
        response.assessmentTitles = course.getOutline().getAssessmentsTitles().toArray(response.assessmentTitles);
        response.assessmentNumberOfInstances = new Integer[course.getOutline().getAssessmentsNumberOfInstances().size()];
        response.assessmentNumberOfInstances = course.getOutline().getAssessmentsNumberOfInstances().toArray(response.assessmentNumberOfInstances);
        response.assessmentWeights = new Double[course.getOutline().getAssessmentsWeights().size()];
        response.assessmentWeights = course.getOutline().getAssessmentsWeights().toArray(response.assessmentWeights);
        response.runningGrade = Double.toString(course.getOutline().computeRunningGrade());
        response.hypotheticalGrade = Double.toString(course.getOutline().computeHypotheticalGrade());
        return response;
    }
}
