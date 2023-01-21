package usecases.account.AddSemesterCourse;

import entities.account.Account;
import entities.course.Course;
import entities.course.Outline;
import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.viewSemester.ViewSemesterResponse;
import ports.usecases.PathNotFoundError;
import ports.usecases.account.addSemesterCourse.AddSemesterCourseInputBoundary;
import ports.usecases.account.addSemesterCourse.AddSemesterCourseRequest;
import usecases.gpaTrend.GetAccountTrendUseCase;

import java.util.ArrayList;
import java.util.List;

public class AddSemesterCourseUseCase implements AddSemesterCourseInputBoundary {

    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;

    public AddSemesterCourseUseCase(EntityGateway entityGateway, EntityFactory entityFactory) {
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
    }

    @Override
    public ViewSemesterResponse execute(AddSemesterCourseRequest request)
            throws PathNotFoundError, AddSemesterCourseInputBoundary.AddSemesterCourseError {
        if (!entityGateway.existsAccount(request.username)) {
            throw new PathNotFoundError("Account not found.");
        }
        Account account = entityGateway.loadAccount(request.username);


        if (account.getArchive().getCourseByCode(request.courseCode) != null ||
                account.getSemester().getCourseByCode(request.courseCode) != null) {
            throw new AddSemesterCourseError("Course Code must be unique");
        }


        if (request.courseName.length() > 50) {
            throw new AddSemesterCourseError("Course name must be less than 30 characters");
        }

        if (request.courseName.equals("")) {
            throw new AddSemesterCourseError("Course name cannot be empty");
        }


        float credit;
        try {
            credit = Float.parseFloat(request.credit);
        } catch (NumberFormatException ex) {
            throw new InvalidCreditError();
        }

        if (credit != 0.5 && credit != 1.0) {
            throw new AddSemesterCourseError("Credit must be either 0.5 or 1.0");
        }

        Outline outline = entityFactory.createOutline();
        Course course = entityFactory.createCourse();
        course.setCourseCode(request.courseCode);
        course.setCourseName(request.courseName);
        course.setOutline(outline);
        course.setCredit(credit);
        account.getSemester().addCourse(course);
        entityGateway.saveAccount(account);
        return createResponse(account);
    }

    private ViewSemesterResponse createResponse(Account account) {
        ViewSemesterResponse response = new ViewSemesterResponse();
        response.username = account.getUsername();
        response.semesterTitle = account.getSemester().getTitle();
        List<String> myList = new ArrayList<>();
        for(Course course : account.getSemester().getRunningCourses()){
            myList.add(course.getCourseCode());
        }
        response.courseCodes = myList.toArray(new String[0]);
        List<String> myTitles = new ArrayList<>();
        for(Course course : account.getSemester().getRunningCourses()){
            myTitles.add(course.getCourseName());
        }
        response.courseTitles = myTitles.toArray(new String[0]);
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
