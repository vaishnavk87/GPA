package usecases.course;

import entities.account.Account;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.course.editCourseData.EditCourseDataInputBoundary;
import ports.usecases.course.editCourseData.EditCourseDataRequest;
import ports.usecases.course.editCourseData.EditCourseDataResponse;

public class EditCourseDataUseCase {

    private EntityGateway entityGateway;

    public EditCourseDataUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public EditCourseDataResponse execute(EditCourseDataRequest request)
    throws ports.usecases.PathNotFoundError, EditCourseDataInputBoundary.EditCourseDataError {

        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError();
        }

        Account account = entityGateway.loadAccount(request.username);
        Course course = account.getSemester().getCourseByCode(request.courseCode);

        if (course == null) {
            throw new PathNotFoundError();
        }

        if (request.newCourseCode.equals(course.getCourseCode())) {
            throw new EditCourseDataInputBoundary.EditCourseDataError("Course code must be different from previous course code");
            }
        if (account.getArchive().getCourseByCode(request.newCourseCode) != null ||
                    account.getSemester().getCourseByCode(request.newCourseCode) != null) {
            throw new EditCourseDataInputBoundary.EditCourseDataError("Course code must be unique");
            }


        if (request.newCourseName.length() > 50) {
             throw new EditCourseDataInputBoundary.EditCourseDataError("Course name must be less than 30 characters");
        }

        if (request.newCourseName.equals(course.getCourseName())) {
            throw new EditCourseDataInputBoundary.EditCourseDataError("Course name must be different from previous course name");
            }

        if (request.newCourseName.equals("")) {
            throw new EditCourseDataInputBoundary.EditCourseDataError("Course name cannot be empty");
            }


        if (request.newCredit != 0.5 || request.newCredit != 1.0) {
            throw new EditCourseDataInputBoundary.EditCourseDataError("Credit must be either 0.5 or 1.0");
        }

        if (request.newCredit == course.getCredit()) {
            throw new EditCourseDataInputBoundary.EditCourseDataError("Credit must be different from previous credit");
        }

        course.setCourseCode(request.newCourseCode);
        course.setCourseName(request.newCourseName);
        course.setCredit(request.newCredit);

        entityGateway.saveAccount(account);

        return createResponse(course);
    }

    private EditCourseDataResponse createResponse(Course course) {
        EditCourseDataResponse response = new EditCourseDataResponse();
        response.newCourseCode = course.getCourseCode();
        response.newCourseName = course.getCourseName();
        response.newCredit = course.getCredit();
        return response;
    }

}
