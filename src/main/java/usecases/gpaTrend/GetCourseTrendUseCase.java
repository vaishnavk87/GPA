package usecases.gpaTrend;

import entities.account.Account;
import entities.assessment.Assessment;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.PathNotFoundError;
import ports.usecases.gpaTrend.TrendModel;
import ports.usecases.gpaTrend.getCourseTrend.GetCourseTrendInputBoundary;

import java.util.ArrayList;
import java.util.List;

// TODO: implement testing
public class GetCourseTrendUseCase implements GetCourseTrendInputBoundary {
    private final EntityGateway entityGateway;

    public GetCourseTrendUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    @Override
    public TrendModel execute(String username, String courseCode) {
        if (!entityGateway.existsAccount(username)) {
            throw new PathNotFoundError("Username: " + username);
        }
        Account account = entityGateway.loadAccount(username);
        Course course = account.getSemester().getCourseByCode(courseCode);
        if (course == null) {
            throw new PathNotFoundError("Course: " + courseCode);
        }

        return calculateTrend(course);
    }

    private TrendModel calculateTrend(Course course) {
        List<Assessment> assessments = course.getOutline().getAssessments();
        List<String> assessment_names = new ArrayList<>();
        List<Double> grades = new ArrayList<>();
        for (Assessment assessment : assessments) {
            double[] part_marks = assessment.getCommittedMarks();
            if (part_marks.length == 0) {
                continue;
            }
            double grade = 0;
            for (double part_mark : part_marks) {
                grade += part_mark;
            }
            assessment_names.add(assessment.getTitle());
            grades.add(grade / part_marks.length);  // Average
        }
        return new TrendModel(assessment_names, grades);
    }
}
