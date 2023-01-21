package ports.usecases.account.addSemesterCourse;

import ports.usecases.account.viewSemester.ViewSemesterResponse;

public interface AddSemesterCourseInputBoundary {
    /**
     * add a new course to the current semester of the user's account
     *
     * @param request a request for a new course with the course's courseCode, courseName, and credit
     * @throws AddSemesterCourseError if the course can't be added
     */
    ViewSemesterResponse execute(AddSemesterCourseRequest request) throws AddSemesterCourseError;

    class AddSemesterCourseError extends Error {
        public AddSemesterCourseError(String message) {
            super(message);
        }
    }

    class InvalidCreditError extends Error {
        public InvalidCreditError() {
            super("Invalid credit.");
        }
    }
}
