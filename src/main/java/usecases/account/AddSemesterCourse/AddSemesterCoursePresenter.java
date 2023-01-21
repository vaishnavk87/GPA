package usecases.account.AddSemesterCourse;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.viewSemester.ViewSemesterResponse;
import views.SemesterView;

import javax.swing.*;

public class AddSemesterCoursePresenter {
    private final JFrame frame;
    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;
    private final JFrame parentFrame;

    public AddSemesterCoursePresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
        this.frame = frame;
        this.parentFrame = parentFrame;
    }

    public void presentError(Throwable error) {
        JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void presentSuccess(ViewSemesterResponse response) {
        JOptionPane.showMessageDialog(null, "Course Added", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.frame.dispose();
        this.parentFrame.dispose();
        new SemesterView(entityGateway, entityFactory, response, parentFrame);
    }
}
