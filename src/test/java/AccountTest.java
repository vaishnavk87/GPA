import entities.account.Account;
import entities.account.Semester;
import inMemoryDB.InMemoryEntityFactory;
import inMemoryDB.InMemoryEntityGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.addAccount.AddAccountRequest;
import ports.usecases.account.loginAccount.LoginAccountRequest;
import usecases.account.AddAccount.AddAccountUseCase;
import usecases.account.LoginAccount.LoginAccountUseCase;

public class AccountTest {
    private EntityGateway entityGateway;
    private EntityFactory entityFactory;

    @Test
    public void AddAccountTest(){
        this.entityGateway = new InMemoryEntityGateway();
        this.entityFactory = new InMemoryEntityFactory();
        AddAccountUseCase myUseCase = new AddAccountUseCase(entityGateway, entityFactory);
        AddAccountRequest request = new AddAccountRequest("55555555", "10101010", "10101010");
        myUseCase.execute(request);
        Assertions.assertEquals("10101010", entityGateway.loadAccount("55555555").getPassword());
    }

    @Test
    public void LoginAccountTest(){
        this.entityGateway = new InMemoryEntityGateway();
        this.entityFactory = new InMemoryEntityFactory();
        Account account = entityFactory.createAccount();
        account.setUsername("11111111");
        account.setPassword("12345678");
        Semester semester = entityFactory.createSemester();
        account.setSemester(semester);
        entityGateway.saveAccount(account);
        LoginAccountRequest request = new LoginAccountRequest("11111111", "12345678");
        LoginAccountUseCase myUseCase = new LoginAccountUseCase(entityGateway);
        myUseCase.execute(request);
    }
}

