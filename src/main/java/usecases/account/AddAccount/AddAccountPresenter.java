package usecases.account.AddAccount;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import views.LoginView;

import javax.swing.*;

public class AddAccountPresenter {
    private final JFrame frame;
    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;

    public AddAccountPresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory) {
        this.frame = frame;
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
    }

    public void presentError(Throwable error) {
        JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void presentSuccess() {
        JOptionPane.showMessageDialog(null, "Account Created", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.frame.dispose();
        new LoginView(this.entityGateway, this.entityFactory);
    }
}
