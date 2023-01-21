package entities.course;

import java.util.ArrayList;


public abstract class Course {
    public abstract String getCourseCode();

    public abstract String getCourseName();

    public abstract float getCredit();

    public abstract Outline getOutline();

    public abstract ArrayList<CourseEvent> getCourseEvents();

    public abstract CourseEvent getCourseEventByTitle(String courseEventTitle);

    public abstract void setCourseCode(String courseCode);

    public abstract void setCourseName(String courseName);

    public abstract void setCredit(float credit);

    public abstract void setOutline(Outline outline);

    public abstract void addCourseEvent(CourseEvent courseEvent);

    public abstract void removeCourseEvent(CourseEvent courseEvent);

    // TODO: expand to change when some values are empty
    @Override
    public String toString() {
        return String.format("%s: %s - %.1f credits", getCourseCode(), getCourseName(), getCredit());
    }

    public interface CourseFactory {
        Course createCourse();
    }
}