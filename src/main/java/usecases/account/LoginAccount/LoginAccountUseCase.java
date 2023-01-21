package usecases.account.LoginAccount;

import entities.account.Account;
import entities.course.Course;
import ports.database.EntityGateway;
import ports.usecases.account.viewAccount.ViewAccountResponse;
import ports.usecases.account.viewSemester.ViewSemesterResponse;
import ports.usecases.account.loginAccount.LoginAccountInputBoundary;
import ports.usecases.account.loginAccount.LoginAccountRequest;
import usecases.gpaTrend.GetAccountTrendUseCase;

// TODO: implement testing
public class LoginAccountUseCase implements LoginAccountInputBoundary {
    private final EntityGateway entityGateway;

    public LoginAccountUseCase(EntityGateway entityGateway) {
        this.entityGateway = entityGateway;
    }

    public ViewAccountResponse execute(LoginAccountRequest request) throws LoginError {
        if (!entityGateway.existsAccount(request.username)) {
            throw new LoginError("Username not found.");
        }
        Account account = entityGateway.loadAccount(request.username);
        if (!account.getPassword().equals(request.password)) {
            throw new LoginError("Incorrect Password");
        }
        return createResponse(account);
    }

    private ViewAccountResponse createResponse(Account account){
        ViewAccountResponse response = new ViewAccountResponse();
        response.username = account.getUsername();
        return response;
    }
}
