package ports.usecases.course.editCourseEventData;

import ports.usecases.course.editCourseEventData.EditCourseEventDataRequest;
import ports.usecases.course.editCourseEventData.EditCourseEventDataResponse;

public interface EditCourseEventDataInputBoundary {
    /**
     * @param request the path to the Course Event and the data to set
     * @return the course event's data
     * @throws ports.usecases.PathNotFoundError                                                                    if the path to the Course Event does not exist
     * @throws ports.usecases.course.editCourseEventData.EditCourseEventDataInputBoundary.EditCourseEventDataError if the new title, day, startTime, endTime, or location is invalid
     */

    public EditCourseEventDataResponse execute(EditCourseEventDataRequest request)
            throws ports.usecases.PathNotFoundError, ports.usecases.course.editCourseEventData.EditCourseEventDataInputBoundary.EditCourseEventDataError;

    public class EditCourseEventDataError extends Error {
        public EditCourseEventDataError(String message) {
            super(message);
        }
    }
}
