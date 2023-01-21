package usecases.gpaTrend;

import entities.account.Account;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.gpaTrend.TrendModel;
import ports.usecases.gpaTrend.getAccountTrend.GetAccountTrendInputBoundary;

import java.util.ArrayList;
import java.util.List;

//TODO: implement testing
public class GetAccountTrendUseCase implements GetAccountTrendInputBoundary {
    private final EntityGateway entityGateway;

    public GetAccountTrendUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    @Override
    public TrendModel execute(String username) {
        if (!entityGateway.existsAccount(username)) {
            throw new PathNotFoundError("Username: " + username);
        }
        Account account = entityGateway.loadAccount(username);
        return calculateTrend(account);
    }

    private TrendModel calculateTrend(Account account) {
        List<String> course_names = new ArrayList<>();
        List<Double> grades = new ArrayList<>();
        for (Course course : account.getSemester().getRunningCourses()) {
            double grade = course.getOutline().computeRunningGrade();
            course_names.add(course.getCourseCode());
            grades.add(grade);
        }
        return new TrendModel(course_names, grades);
    }
}
