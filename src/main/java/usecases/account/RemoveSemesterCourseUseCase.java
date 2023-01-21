package usecases.account;

import entities.account.Account;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.account.RemoveSemesterCourse.RemoveSemesterCourseInputBoundary;
import ports.usecases.account.RemoveSemesterCourse.RemoveSemesterCourseRequest;
import ports.usecases.account.RemoveSemesterCourse.RemoveSemesterCourseResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoveSemesterCourseUseCase implements RemoveSemesterCourseInputBoundary {

    private EntityGateway entityGateway;

    public RemoveSemesterCourseUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    @Override
    public RemoveSemesterCourseResponse execute(RemoveSemesterCourseRequest request) throws CourseDoesNotExistError {
        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }
        Account account = entityGateway.loadAccount(request.username);
        if ((account.getSemester().getCourseByCode(request.courseCode) == null)) {
            throw new RemoveSemesterCourseInputBoundary.CourseDoesNotExistError();
        }
        account.getSemester().removeCourse(account.getSemester().getCourseByCode(request.courseCode));
        entityGateway.saveAccount(account);
        return createResponse(account);
    }

    private RemoveSemesterCourseResponse createResponse(Account account) {
        RemoveSemesterCourseResponse response = new RemoveSemesterCourseResponse();
        List<String> myList = new ArrayList<>();
        for(Course course : account.getSemester().getRunningCourses()){
            myList.add(course.getCourseCode());
        }
        response.courseList = myList;
        return response;
    }
}
