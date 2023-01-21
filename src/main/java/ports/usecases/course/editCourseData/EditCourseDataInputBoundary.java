package ports.usecases.course.editCourseData;

public interface EditCourseDataInputBoundary {
    /**
     * @param request the path to the Course and the courseCode, courseName, and credit to set
     * @return the courseCode, courseName, and credit that was set
     * @throws ports.usecases.PathNotFoundError                if the path to the Course does not exist
     * @throws EditCourseDataInputBoundary.EditCourseDataError if the new courseCode, courseName, or credit is invalid
     */

    public EditCourseDataResponse execute(EditCourseDataRequest request)
            throws ports.usecases.PathNotFoundError, EditCourseDataInputBoundary.EditCourseDataError;

    public class EditCourseDataError extends Error {
        public EditCourseDataError(String message) {
            super(message);
        }
    }
}
