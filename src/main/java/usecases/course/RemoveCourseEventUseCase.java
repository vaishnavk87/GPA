package usecases.course;

import entities.account.Account;
import entities.course.Course;
import entities.course.CourseEvent;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.course.removeCourseEvent.RemoveCourseEventRequest;
import ports.usecases.course.removeCourseEvent.RemoveCourseEventResponse;

public class RemoveCourseEventUseCase {

    private EntityGateway entityGateway;

    public RemoveCourseEventUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public RemoveCourseEventResponse execute(RemoveCourseEventRequest request) {

        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }

        Account account = entityGateway.loadAccount(request.username);
        Course course = account.getSemester().getCourseByCode(request.courseCode);

        if (course == null) {
            throw new PathNotFoundError();
        }
        if (course.getCourseEventByTitle(request.eventTitle) == null) {
            throw new PathNotFoundError();
        }

        CourseEvent courseEvent = course.getCourseEventByTitle(request.eventTitle);

        course.removeCourseEvent(courseEvent);

        entityGateway.saveAccount(account);

        return createResponse(course);
    }

    private RemoveCourseEventResponse createResponse(Course course) {
        return new RemoveCourseEventResponse(course.getCourseEvents());
    }
}

