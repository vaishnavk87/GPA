package ports.usecases.account.viewAccount;

public class ViewAccountResponse {

    public String username;

    public String semesterTitle;

    public ViewAccountResponse(){

    }
    public ViewAccountResponse(String username, String semesterTitle) {
        this.username = username;
        this.semesterTitle = semesterTitle;
    }
}
