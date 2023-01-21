package ports.usecases.account.removeAchiveCourse;

import java.util.List;

public class RemoveArchiveCourseResponse {
    public List<String> courseList;

    public RemoveArchiveCourseResponse() {
    }

    public RemoveArchiveCourseResponse(List<String> courses) {
        this.courseList = courses;
    }
}
