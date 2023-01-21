package usecases.account.ArchiveCourse;

import entities.account.Account;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.account.viewSemester.ViewSemesterResponse;
import ports.usecases.PathNotFoundError;
import ports.usecases.account.archiveCourse.ArchiveCourseInputBoundary;
import usecases.gpaTrend.GetAccountTrendUseCase;

public class ArchiveCourseUseCase implements ArchiveCourseInputBoundary {
    private final EntityGateway entityGateway;

    public ArchiveCourseUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    @Override
    public ViewSemesterResponse execute(String username, String courseCode) throws PathNotFoundError, CourseNotCompletedError {
        if (!entityGateway.existsAccount(username)) {
            throw new PathNotFoundError("Username: " + username);
        }
        Account account = entityGateway.loadAccount(username);
        Course course = account.getSemester().getCourseByCode(courseCode);
        if (course == null) {
            throw new PathNotFoundError("Course: " + courseCode);
        }
        //if (course.getOutline().getPercentageCompleted() < 1.0) {
        //    throw new CourseNotCompletedError();
        //}
        account.getSemester().removeCourse(course);
        account.getArchive().addCourse(course, account.getSemester().getTitle());
        entityGateway.saveAccount(account);
        return createResponse(account);
    }

    private ViewSemesterResponse createResponse(Account account) {
        ViewSemesterResponse response = new ViewSemesterResponse();
        response.username = account.getUsername();
        response.courseCodes = account.getSemester().getRunningCourses().stream().map(Course::getCourseCode).toArray(String[]::new);
        response.courseTitles = account.getSemester().getRunningCourses().stream().map(Course::getCourseName).toArray(String[]::new);
        response.courseGrades = new Double[response.courseCodes.length];
        int index = 0;
        for (Course course : account.getSemester().getRunningCourses()) {
            response.courseGrades[index] = course.getOutline().computeRunningGrade();
            index += 1;
        }
        response.trendModel = new GetAccountTrendUseCase(entityGateway).execute(account.getUsername());
        return response;
    }
}
