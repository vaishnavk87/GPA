package usecases.account.AddAccount;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.addAccount.AddAccountRequest;

import javax.swing.*;

public class AddAccountController {
    public AddAccountController(AddAccountRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory) {
        AddAccountUseCase usecase = new AddAccountUseCase(entityGateway, entityFactory);
        AddAccountPresenter presenter = new AddAccountPresenter(frame, entityGateway, entityFactory);

        try {
            usecase.execute(request);
            presenter.presentSuccess();
        } catch (Throwable error) {
            presenter.presentError(error);
        }
    }
}
