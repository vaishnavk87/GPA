package ports.usecases.course.editCourseEventData;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class EditCourseEventDataRequest {
    public String username; /*abstract to path class*/

    public String courseCode; /*abstract to path class*/

    public String eventTitle; /*abstract to path class*/

    public String newTitle;

    public DayOfWeek newDay;

    public LocalTime newStartTime;

    public LocalTime newEndTime;

    public String newLocation;

    public EditCourseEventDataRequest() {
    }

    public EditCourseEventDataRequest(String username, String courseCode, String eventTitle, String newTitle,
                                      DayOfWeek newDay, LocalTime newStartTime, LocalTime newEndTime, String newLocation) {
        this.username = username;
        this.courseCode = courseCode;
        this.eventTitle = eventTitle;
        this.newTitle = newTitle;
        this.newDay = newDay;
        this.newStartTime = newStartTime;
        this.newEndTime = newEndTime;
        this.newLocation = newLocation;

    }
}
