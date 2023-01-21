package ports.usecases.account.loginAccount;

public class LoginAccountRequest {
    public String username;
    public String password;

    public LoginAccountRequest() {
    }

    public LoginAccountRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
