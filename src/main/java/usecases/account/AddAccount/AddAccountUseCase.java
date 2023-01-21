package usecases.account.AddAccount;

import entities.account.Account;
import entities.account.Semester;
import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.addAccount.AddAccountInputBoundary;
import ports.usecases.account.addAccount.AddAccountRequest;

// TODO: implement testing
public class AddAccountUseCase implements AddAccountInputBoundary {
    // private final Account.AccountFactory accountFactory;
    // private final Semester.SemesterFactory semesterFactory;
    // private final Archive.ArchiveFactory archiveFactory;
    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;

    public AddAccountUseCase(
            EntityGateway entityGateway,
            EntityFactory entityFactory
    ) {
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
    }

    public void execute(AddAccountRequest request) {
        if (entityGateway.existsAccount(request.username)) {
            throw new UsernameAlreadyInUseError(request.username);
        }
        if (!Account.isUsernameValid(request.username)) {
            throw new UsernameNotValidError();
        }
        if (!Account.isPasswordValid(request.password)) {
            throw new PasswordNotValidError();
        }
        if (!request.password.equals(request.passwordRepeat)) {
            throw new PasswordsDoNotMatchError();
        }

        Account account = entityFactory.createAccount();
        setFields(account, request);
        entityGateway.saveAccount(account);
    }

    private void setFields(Account account, AddAccountRequest request) {
        account.setUsername(request.username);
        account.setPassword(request.password);
        Semester semester = entityFactory.createSemester();
        semester.setTitle("My Semester");
        account.setSemester(semester);
        account.setArchive(entityFactory.createArchive());
    }
}
