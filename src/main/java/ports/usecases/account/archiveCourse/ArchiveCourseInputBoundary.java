package ports.usecases.account.archiveCourse;

import ports.usecases.account.viewSemester.ViewSemesterResponse;
import ports.usecases.PathNotFoundError;

public interface ArchiveCourseInputBoundary {
    /**
     * Send a course to this account's archive. The course must be completed (i.e., all instances are committed)
     *
     * @param username   the username of the account
     * @param courseCode the courseCode
     * @return
     * @throws PathNotFoundError if the username or courseCode do not exist
     */
    ViewSemesterResponse execute(String username, String courseCode) throws PathNotFoundError, CourseNotCompletedError;

    class CourseNotCompletedError extends Error {
    }
}
