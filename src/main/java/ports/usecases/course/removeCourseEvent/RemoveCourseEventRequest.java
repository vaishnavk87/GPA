package ports.usecases.course.removeCourseEvent;

public class RemoveCourseEventRequest {
    public String username; /*abstract to path class*/

    public String courseCode; /*abstract to path class*/

    public String eventTitle; /*abstract to path class*/

    public RemoveCourseEventRequest() {
    }

    ;

    public RemoveCourseEventRequest(String username, String courseCode, String eventTitle) {
        this.username = username;
        this.courseCode = courseCode;
        this.eventTitle = eventTitle;
    }
}

