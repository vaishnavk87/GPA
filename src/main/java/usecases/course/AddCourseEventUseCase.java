package usecases.course;

import entities.account.Account;
import entities.course.Course;
import entities.course.CourseEvent;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.course.addCourseEvent.AddCourseEventInputBoundary;
import ports.usecases.course.addCourseEvent.AddCourseEventRequest;
import ports.usecases.course.addCourseEvent.AddCourseEventResponse;

public class AddCourseEventUseCase {

    private EntityGateway entityGateway;
    private CourseEvent.CourseEventFactory courseEventFactory;

    public AddCourseEventUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public AddCourseEventResponse execute(AddCourseEventRequest request) throws PathNotFoundError, AddCourseEventInputBoundary.AddCourseEventError {

        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }

        Account account = entityGateway.loadAccount(request.username);
        Course course = account.getSemester().getCourseByCode(request.courseCode);

        if (course == null) {
            throw new PathNotFoundError();
        }
        CourseEvent courseEvent = courseEventFactory.createCourseEvent(request.eventTitle, request.day, request.startTime, request.endTime, request.location);
        course.addCourseEvent(courseEvent);

        entityGateway.saveAccount(account);

        return createResponse(course);
    }

    private AddCourseEventResponse createResponse(Course course) {
        return new AddCourseEventResponse(course.getCourseEvents());
    }
}
