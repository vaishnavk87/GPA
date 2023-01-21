package inMemoryDB.entities;

import entities.account.Semester;
import entities.course.Course;

import java.io.Serializable;
import java.util.ArrayList;

public class SemesterImpl extends Semester implements Serializable {
    // TODO: update the implementation so that it uses a hashmap from courseCodes to Course objects.

    private static final String DEFAULT_SEMESTER_TITLE = "Semester";
    private String title = DEFAULT_SEMESTER_TITLE;
    private final ArrayList<Course> runningCourses = new ArrayList<>();

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public ArrayList<Course> getRunningCourses() {
        return runningCourses;
    }

    @Override
    public void addCourse(Course course) {
        if (!getRunningCourses().contains(course)) {
            getRunningCourses().add(course);
        }
    }

    @Override
    public void removeCourse(Course course) {
        getRunningCourses().remove(course);
    }

    @Override
    public Course getCourseByCode(String courseCode) {
        for (Course course : getRunningCourses()) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
