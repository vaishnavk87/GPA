package ports.usecases.account.RemoveSemesterCourse;

import java.util.List;

public class RemoveSemesterCourseResponse {
    public List<String> courseList;

    public RemoveSemesterCourseResponse() {
    }

    public RemoveSemesterCourseResponse(List<String> courses) {
        this.courseList = courses;
    }
}
