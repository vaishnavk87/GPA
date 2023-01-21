package ports.usecases.course.editCourseEventData;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class EditCourseEventDataResponse {
    public String newTitle;

    public DayOfWeek newDay;

    public LocalTime newStartTime;

    public LocalTime newEndTime;

    public String newLocation;

    public EditCourseEventDataResponse() {
    }

    public EditCourseEventDataResponse(String newTitle, DayOfWeek newDay, LocalTime newStartTime, LocalTime newEndTime, String newLocation) {
        this.newTitle = newTitle;
        this.newDay = newDay;
        this.newStartTime = newStartTime;
        this.newEndTime = newEndTime;
        this.newLocation = newLocation;
    }
}
