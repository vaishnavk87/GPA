package usecases.account.ViewAccount;

import entities.account.Account;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.account.viewAccount.ViewAccountInputBoundary;
import ports.usecases.account.viewAccount.ViewAccountRequest;
import ports.usecases.account.viewAccount.ViewAccountResponse;
import ports.usecases.account.viewSemester.ViewSemesterResponse;
import usecases.gpaTrend.GetAccountTrendUseCase;

public class ViewAccountUseCase implements ViewAccountInputBoundary {

    private final EntityGateway entityGateway;

    public ViewAccountUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public ViewSemesterResponse execute (ViewAccountRequest request) {
        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError("");
        }
        Account account = entityGateway.loadAccount(request.username);
        return createResponse(account);
    }

    private ViewSemesterResponse createResponse(Account account) {
        ViewSemesterResponse response = new ViewSemesterResponse();
        response.username = account.getUsername();
        response.semesterTitle = account.getSemester().getTitle();
        response.courseCodes = account.getSemester().getRunningCourses().stream()
                .map(Course::getCourseCode)
                .toArray(String[]::new);
        response.courseTitles = account.getSemester().getRunningCourses().stream()
                .map(Course::getCourseName)
                .toArray(String[]::new);
        response.courseGrades = new Double[response.courseCodes.length];
        int index = 0;
        for (Course course : account.getSemester().getRunningCourses()) {
            response.courseGrades[index] = course.getOutline().computeRunningGrade();
            index += 1;
        }
        response.trendModel = new GetAccountTrendUseCase(entityGateway).execute(account.getUsername());
        return response;
    }
}
