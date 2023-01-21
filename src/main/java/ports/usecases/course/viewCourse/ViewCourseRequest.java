package ports.usecases.course.viewCourse;

public class ViewCourseRequest {
    public String username;
    public String courseCode;
    public String semesterTitle;

    public ViewCourseRequest(String username, String courseCode, String semesterTitle) {
        this.username = username;
        this.courseCode = courseCode;
        this.semesterTitle = semesterTitle;
    }
}

