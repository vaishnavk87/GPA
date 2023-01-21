package ports.usecases.course.addCourseEvent;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class AddCourseEventRequest {
    public String username; /*abstract to path class*/
    public String courseCode; /*abstract to path class*/

    public String eventTitle;
    public DayOfWeek day;
    public LocalTime startTime;
    public LocalTime endTime;
    public String location;

    public AddCourseEventRequest() {
    }

    ;

    public AddCourseEventRequest(String username, String courseCode, String eventTitle, DayOfWeek day, LocalTime startTime,
                                 LocalTime endTime, String location) {
        this.username = username;
        this.courseCode = courseCode;
        this.eventTitle = eventTitle;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }


}
