package ports.usecases.account.RemoveSemesterCourse;

public interface RemoveSemesterCourseInputBoundary {
    /**
     * Remove an existing course from the user's account's semester.
     *
     * @param request the user input required for this usecase
     * @throws CourseDoesNotExistError if the course requested does not exist in semester
     */
    RemoveSemesterCourseResponse execute(RemoveSemesterCourseRequest request) throws CourseDoesNotExistError;

    class CourseDoesNotExistError extends Error {
    }
}
