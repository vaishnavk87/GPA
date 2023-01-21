package entities.course;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CourseTest {
    private class CourseMock extends Course {
        private String courseCode;
        private String courseName;
        private float credit;
        private Outline outline;
        private ArrayList<CourseEvent> courseEvents;

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

    @Test
    public void toStringTest() {
        Course course = createCourse();
        Assertions.assertEquals("CSC207: Software Design - 0.5 credits", course.toString());
    }

    private Course createCourse() {
        Course course = new CourseMock();
        course.setCourseCode("CSC207");
        course.setCourseName("Software Design");
        course.setCredit(0.5f);
        return course;
    }
}
