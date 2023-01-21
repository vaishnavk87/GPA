package ports.usecases.account.viewSemester;

import ports.usecases.gpaTrend.TrendModel;

public class ViewSemesterResponse {
    public String username;
    public String semesterTitle;
    public String[] courseCodes;
    public String[] courseTitles;
    public Double[] courseGrades;

    public Double[] courseCredits;

    public String runningGPA;

    public String hypotheticalGPA;
    public TrendModel trendModel;

    public ViewSemesterResponse() {
    }

    public ViewSemesterResponse(String username, String semesterTitle, String[] courseCodes, String[] courseTitles, Double[] courseGrades,
                                String runningGPA, String hypotheticalGPA, TrendModel trendModel) {
        this.username = username;
        this.semesterTitle = semesterTitle;
        this.courseCodes = courseCodes;
        this.courseTitles = courseTitles;
        this.courseGrades = courseGrades;
        this.runningGPA = runningGPA;
        this.hypotheticalGPA = hypotheticalGPA;
        this.trendModel = trendModel;
    }
}
