package ports.usecases.account.archiveCourse;

public class ArchiveCourseRequest {
    public String courseCode;
    public String username;

    public ArchiveCourseRequest(String username, String courseCode) {
        this.username = username;
        this.courseCode = courseCode;
    }
}
