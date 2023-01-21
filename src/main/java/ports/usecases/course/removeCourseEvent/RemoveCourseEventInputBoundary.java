package ports.usecases.course.removeCourseEvent;

import ports.usecases.course.removeCourseEvent.RemoveCourseEventRequest;
import ports.usecases.course.removeCourseEvent.RemoveCourseEventResponse;

public interface RemoveCourseEventInputBoundary {
    /**
     * @param request the path to the Course and the event to Remove described by its type, day, start time, end time, and location
     * @return the list of events in the Course
     * @throws ports.usecases.PathNotFoundError                                                              if the path to the Course does not exist
     * @throws ports.usecases.course.removeCourseEvent.RemoveCourseEventInputBoundary.RemoveCourseEventError if the course event couldn't be removed
     */

    public RemoveCourseEventResponse execute(RemoveCourseEventRequest request)
            throws ports.usecases.PathNotFoundError, ports.usecases.course.removeCourseEvent.RemoveCourseEventInputBoundary.RemoveCourseEventError;

    public class RemoveCourseEventError extends Error {
        public RemoveCourseEventError(String message) {
            super(message);
        }
    }
}