package entities.course;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public abstract class CourseEvent {
    public abstract String getTitle();
    public abstract DayOfWeek getDay();
    public abstract LocalTime getStartTime();
    public abstract LocalTime getEndTime();
    public abstract String getLocation();

    public abstract void setTitle(String type);
    public abstract void setDay(DayOfWeek day);
    public abstract void setStartTime(LocalTime startTime);
    public abstract void setEndTime(LocalTime endTime);
    public abstract void setLocation(String location);

    public Duration getDuration() {
        return Duration.between(getStartTime(), getEndTime());
    }

    public String toString() {
        return String.format("%s - %s from %s to %s - %s",
                getTitle(),
                getDay(),
                getStartTime(),
                getEndTime(),
                getLocation()
        );
    }

    public interface CourseEventFactory {
        CourseEvent createCourseEvent(String type, DayOfWeek day, LocalTime startTime, LocalTime endTime,
                                             String location);
    }
}
