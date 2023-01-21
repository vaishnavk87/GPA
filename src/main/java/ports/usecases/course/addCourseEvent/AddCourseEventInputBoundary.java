package ports.usecases.course.addCourseEvent;

public interface AddCourseEventInputBoundary {
    /**
     * @param request the path to the Course and the event to add described by its type, day, start time, end time, and location
     * @return the list of events in the Course
     * @throws ports.usecases.PathNotFoundError                if the path to the Course does not exist
     * @throws AddCourseEventInputBoundary.AddCourseEventError if any of the event metadata is invalid
     */

    public AddCourseEventResponse execute(AddCourseEventRequest request)
            throws ports.usecases.PathNotFoundError, AddCourseEventInputBoundary.AddCourseEventError;

    public class AddCourseEventError extends Error {
        public AddCourseEventError(String message) {
            super(message);
        }
    }
}
