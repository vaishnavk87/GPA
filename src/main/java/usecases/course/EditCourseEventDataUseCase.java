package usecases.course;

import entities.account.Account;
import entities.course.Course;
import entities.course.CourseEvent;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.course.editCourseEventData.EditCourseEventDataInputBoundary;
import ports.usecases.course.editCourseEventData.EditCourseEventDataRequest;
import ports.usecases.course.editCourseEventData.EditCourseEventDataResponse;

public class EditCourseEventDataUseCase {

    private EntityGateway entityGateway;

    public EditCourseEventDataUseCase(EntityGateway gateway) {
        this.entityGateway = entityGateway;
    }

    public EditCourseEventDataResponse execute(EditCourseEventDataRequest request)
            throws ports.usecases.PathNotFoundError, EditCourseEventDataInputBoundary.EditCourseEventDataError {

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

        if (request.newTitle.equals(request.eventTitle)) {
            throw new EditCourseEventDataInputBoundary.EditCourseEventDataError("New event title cannot be the same as the old event title");
        }

        if (course.getCourseEventByTitle(request.newTitle) != null) {
            throw new EditCourseEventDataInputBoundary.EditCourseEventDataError("New event title must be unique");
        }

        courseEvent.setTitle(request.newTitle);
        courseEvent.setDay(request.newDay);
        courseEvent.setStartTime(request.newStartTime);
        courseEvent.setEndTime(request.newEndTime);
        courseEvent.setLocation(request.newLocation);

        entityGateway.saveAccount(account);

        return createResponse(courseEvent);
    }

    private EditCourseEventDataResponse createResponse(CourseEvent courseEvent) {
        EditCourseEventDataResponse response = new EditCourseEventDataResponse();
        response.newTitle = courseEvent.getTitle();
        response.newDay = courseEvent.getDay();
        response.newStartTime = courseEvent.getStartTime();
        response.newEndTime = courseEvent.getEndTime();
        response.newLocation = courseEvent.getLocation();
        return response;
    }

}
