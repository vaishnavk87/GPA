package usecases.assessment.AddSimpleAssessment;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.course.viewCourse.ViewCourseResponse;
import views.CourseView;

import javax.swing.*;

public class AddSimpleAssessmentPresenter {

    private final JFrame frame;
    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;
    private final JFrame parentFrame;

    public AddSimpleAssessmentPresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
        this.frame = frame;
        this.parentFrame = parentFrame;
    }

    public void presentError(Throwable error) {
        JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void presentSuccess(ViewCourseResponse response) {
        JOptionPane.showMessageDialog(null, "Assessment Added", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.frame.dispose();
        this.parentFrame.dispose();
        new CourseView(entityGateway, entityFactory, response, parentFrame);
    }
}
