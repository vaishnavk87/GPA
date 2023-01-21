package ports.usecases.account.addSemesterCourse;

public class AddSemesterCourseRequest {

    public String username;
    public String courseCode;
    public String courseName;
    public String credit;

    public AddSemesterCourseRequest(String username, String courseCode, String courseName, String credit) {
        this.username = username;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credit = credit;

    }
}
