import inMemoryDB.InMemoryEntityFactory;
import inMemoryDB.InMemoryEntityGateway;
import ports.database.EntityFactory;
import ports.database.EntityGateway;
import views.LoginView;

public class Main {
    private static final String DATABASE_FILE_PATH =
            "src/main/java/database.ser";

    private static final EntityGateway entityGateway = new InMemoryEntityGateway(DATABASE_FILE_PATH);
    private static final EntityFactory entityFactory = new InMemoryEntityFactory();

    public static void main(String[] args) {
        new LoginView(entityGateway, entityFactory);
    }
}
