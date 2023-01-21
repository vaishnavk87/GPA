package usecases.account.LoginAccount;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.viewAccount.ViewAccountResponse;
import ports.usecases.account.viewSemester.ViewSemesterResponse;
import ports.usecases.account.loginAccount.LoginAccountRequest;

import javax.swing.*;

public class LoginAccountController {
    public LoginAccountController(LoginAccountRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory) {
        LoginAccountUseCase usecase = new LoginAccountUseCase(entityGateway);
        LoginAccountPresenter presenter = new LoginAccountPresenter(frame, entityGateway, entityFactory);

        try {
            ViewAccountResponse response = usecase.execute(request);
            presenter.presentSuccess(response);
        } catch (Throwable error) {
            presenter.presentError(error);
        }
    }
}
