package inMemoryDB.entities;

import entities.course.CourseEvent;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class CourseEventImpl extends CourseEvent implements Serializable {
    private String title;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String location;

    /**
     * Create a new CourseEvent object with the given parameters
     * @param title the type of the course event
     *             e.g. 'Lecture', 'Tutorial', 'Lab'
     *             Note: this is not necessarily the same as the name of the course event (there can be two different lectures)
     * @param day the day of the week the course event is held on
     *            e.g. 'Monday', 'Tuesday', etc.
     *            Note: this is a DayOfWeek object, not a String
     * @param startTime the time the course event starts
     *                  e.g. "10:00"
     *                  Note: this is a LocalTime object, not a String
     * @param endTime the time the course event ends
     *                  e.g. "11:00"
     *                  Note: this is a LocalTime object, not a String
     *                  Precondition: this must be after the start time
     *                  Precondition: this must be before the end of the day
     * @param location the location of the course event
     *                 e.g. 'BA 1234'
     */
    public CourseEventImpl(String title, DayOfWeek day, LocalTime startTime, LocalTime endTime, String location) {
        this.title = title;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
