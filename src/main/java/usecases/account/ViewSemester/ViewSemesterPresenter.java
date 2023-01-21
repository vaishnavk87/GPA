package usecases.account.ViewSemester;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.viewSemester.ViewSemesterResponse;
import views.SemesterView;

import javax.swing.*;

public class ViewSemesterPresenter {

    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;
    private final JFrame frame;
    private final JFrame parentFrame;

    ViewSemesterPresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
        this.frame = frame;
        this.parentFrame = parentFrame;
    }

    public void present(ViewSemesterResponse response) {
        new SemesterView(entityGateway, entityFactory, response, parentFrame);
        frame.dispose();
    }
}
