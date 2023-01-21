package ports.usecases.course.viewCourse;

import ports.usecases.PathNotFoundError;

public interface ViewCourseInputBoundary {
    ViewCourseResponse execute(ViewCourseRequest request) throws PathNotFoundError;

}
