package usecases.course.ViewCourse;

import entities.account.Account;
import entities.course.Course;
import entities.gpa.GPACalculation;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.course.viewCourse.ViewCourseInputBoundary;
import ports.usecases.course.viewCourse.ViewCourseRequest;
import ports.usecases.course.viewCourse.ViewCourseResponse;
import usecases.gpaTrend.GetCourseTrendUseCase;

public class ViewCourseUseCase implements ViewCourseInputBoundary {

    private final EntityGateway entityGateway;

    public ViewCourseUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;}

    public ViewCourseResponse execute(ViewCourseRequest request) {
        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }
        Account account = entityGateway.loadAccount(request.username);
        Course course = account.getSemester().getCourseByCode(request.courseCode);
        if (course == null) {
            throw new PathNotFoundError();
        }
        return createResponse(course, account, request.semesterTitle);
    }

    private ViewCourseResponse createResponse(Course course, Account account, String semesterTitle) {
        ViewCourseResponse response = new ViewCourseResponse();
        response.semesterTitle = semesterTitle;
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
        response.runningGrade = Double.toString(Math.round(course.getOutline().computeRunningGrade() * 100.0) / 100.0);
        response.hypotheticalGrade = Double.toString(Math.round(course.getOutline().computeHypotheticalGrade() * 100.0) / 100.0);
        response.runningLetteredGrade = GPACalculation.percentToGrade(course.getOutline().computeRunningGrade());
        response.hypotheticalLetteredGrade = GPACalculation.percentToGrade(course.getOutline().computeHypotheticalGrade());
        response.trendModel = new GetCourseTrendUseCase(entityGateway).execute(account.getUsername(), response.courseCode);
        return response;
    }


}
