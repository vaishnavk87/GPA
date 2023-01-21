package ports.usecases.course.removeCourseEvent;

import entities.course.CourseEvent;

import java.util.ArrayList;

public class RemoveCourseEventResponse {
    public ArrayList<CourseEvent> courseEvents;

    public RemoveCourseEventResponse() {
    }

    public RemoveCourseEventResponse(ArrayList<CourseEvent> courseEvents) {
        this.courseEvents = courseEvents;
    }
}