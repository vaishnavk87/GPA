package ports.usecases.account.loginAccount;

import ports.usecases.account.viewAccount.ViewAccountResponse;
import ports.usecases.account.viewSemester.ViewSemesterResponse;

public interface LoginAccountInputBoundary {
    /**
     * Log into an Account
     *
     * @param request the username and password to use
     * @return preliminary account data
     * @throws LoginError if the username is not found or the password is incorrect.
     */
    ViewAccountResponse execute(LoginAccountRequest request) throws LoginError;


    class LoginError extends Error {
        public LoginError(String message) {
            super("Login Error: " + message);
        }
    }
}
