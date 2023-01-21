package ports.usecases.account.viewSemester;

public class ViewSemesterRequest {
    public String username;
    public String semesterTitle;

    public ViewSemesterRequest(String username, String semesterTitle) {
        this.username = username;
        this.semesterTitle = semesterTitle;
    }
}
