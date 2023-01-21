import entities.account.Account;
import entities.account.Archive;
import entities.account.Semester;
import entities.course.Course;
import inMemoryDB.InMemoryEntityFactory;
import inMemoryDB.InMemoryEntityGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.RemoveSemesterCourse.RemoveSemesterCourseRequest;
import ports.usecases.account.addSemesterCourse.AddSemesterCourseRequest;
import usecases.account.AddSemesterCourse.AddSemesterCourseUseCase;
import usecases.account.RemoveSemesterCourseUseCase;

import java.util.ArrayList;

public class SemesterTest {
    private EntityGateway entityGateway;
    private EntityFactory entityFactory;

    @Test
    public void addSemesterCourseTest(){
        this.entityFactory = new InMemoryEntityFactory();
        this.entityGateway = new InMemoryEntityGateway();
        Account account = entityFactory.createAccount();
        Semester semester = entityFactory.createSemester();
        Archive archive = entityFactory.createArchive();
        account.setUsername("88888888");
        account.setSemester(semester);
        account.setArchive(archive);
        entityGateway.saveAccount(account);
        AddSemesterCourseUseCase myUseCase = new AddSemesterCourseUseCase(entityGateway, entityFactory);
        AddSemesterCourseRequest request = new AddSemesterCourseRequest("88888888", "AAA111", "test", "0.5");
        myUseCase.execute(request);
        Assertions.assertEquals("AAA111", entityGateway.loadAccount("88888888").getSemester().getCourseByCode("AAA111").getCourseCode());
    }

    @Test
    public void removeSemesterCourseTest(){
        this.entityFactory = new InMemoryEntityFactory();
        this.entityGateway = new InMemoryEntityGateway();
        Account account = entityFactory.createAccount();
        Semester semester = entityFactory.createSemester();
        account.setUsername("00000000");
        account.setSemester(semester);
        Course course = entityFactory.createCourse();
        course.setCourseCode("BBB222");
        semester.addCourse(course);
        entityGateway.saveAccount(account);
        RemoveSemesterCourseUseCase myUseCase = new RemoveSemesterCourseUseCase(entityGateway);
        RemoveSemesterCourseRequest request = new RemoveSemesterCourseRequest("00000000", "BBB222");
        myUseCase.execute(request);
        Assertions.assertEquals(new ArrayList<>(), entityGateway.loadAccount("00000000").getSemester().getRunningCourses());
    }
}
