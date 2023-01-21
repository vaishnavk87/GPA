package ports.usecases.account.RemoveSemesterCourse;

public class RemoveSemesterCourseRequest {

    public String username;
    public String courseCode;

    public RemoveSemesterCourseRequest(String username, String courseCode) {
        this.courseCode = courseCode;
        this.username = username;
    }
}
