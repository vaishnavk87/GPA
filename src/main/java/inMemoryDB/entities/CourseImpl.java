package inMemoryDB.entities;

import entities.course.Course;
import entities.course.CourseEvent;
import entities.course.Outline;

import java.io.Serializable;
import java.util.ArrayList;

public class CourseImpl extends Course implements Serializable {
    private String courseCode;
    private String courseName;
    private float credit;
    private Outline outline;
    private final ArrayList<CourseEvent> courseEvents = new ArrayList<>();

    @Override
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public float getCredit() {
        return credit;
    }

    @Override
    public void setCredit(float credit) {
        this.credit = credit;
    }

    @Override
    public Outline getOutline() {
        return outline;
    }

    @Override
    public void setOutline(Outline outline) {
        this.outline = outline;
    }

    @Override
    public ArrayList<CourseEvent> getCourseEvents() {
        return courseEvents;
    }

    @Override
    public CourseEvent getCourseEventByTitle(String title) {
        for (CourseEvent courseEvent : courseEvents) {
            if (courseEvent.getTitle().equals(title)) {
                return courseEvent;
            }
        }
        return null;
    }

    @Override
    public void addCourseEvent(CourseEvent courseEvent) {
        if (!courseEvents.contains(courseEvent)) {
            courseEvents.add(courseEvent);
        }
    }

    @Override
    public void removeCourseEvent(CourseEvent courseEvent) {
        courseEvents.remove(courseEvent);
    }
}