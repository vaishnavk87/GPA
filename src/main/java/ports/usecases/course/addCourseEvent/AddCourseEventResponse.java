package ports.usecases.course.addCourseEvent;

import entities.course.CourseEvent;

import java.util.ArrayList;

public class AddCourseEventResponse {
    public ArrayList<CourseEvent> courseEvents;

    public AddCourseEventResponse() {
    }

    public AddCourseEventResponse(ArrayList<CourseEvent> courseEvents) {
        this.courseEvents = courseEvents;
    }
}
