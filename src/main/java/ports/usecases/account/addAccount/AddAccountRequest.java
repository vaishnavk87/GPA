package ports.usecases.account.addAccount;

public class AddAccountRequest {
    public String username;
    public String password;
    public String passwordRepeat;

    public AddAccountRequest() {
    }

    public AddAccountRequest(String username, String password, String passwordRepeat) {
        this.username = username;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
    }
}
