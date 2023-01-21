package ports.usecases.account.removeAchiveCourse;

import ports.usecases.PathNotFoundError;
import ports.usecases.account.archiveCourse.ArchiveCourseInputBoundary;

public interface RemoveArchiveCourseInputBoundary {
    /**
     * Send remove a course from this account's archive.
     *
     * @param username   the username of the account
     * @param courseCode the name of the course that the user wants to remove
     * @throws PathNotFoundError if the username or courseCode do not exist
     */

    RemoveArchiveCourseResponse execute(String username, String courseCode) throws PathNotFoundError;
}
