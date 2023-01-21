package usecases.assessment.SetMark;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;
import views.AssessmentView;

import javax.swing.*;

public class SetMarkPresenter {

    private final JFrame frame;
    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;
    private final JFrame parentFrame;
    private final JFrame parentParentFrame;

    public SetMarkPresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame, JFrame parentParentFrame) {
        this.frame = frame;
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
        this.parentFrame = parentFrame;
        this.parentParentFrame = parentParentFrame;
    }

    public void presentError(Throwable error) {
        JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void presentSuccess(ViewAssessmentResponse response) {
        JOptionPane.showMessageDialog(null, "Mark Set", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.frame.dispose();
        this.parentFrame.dispose();
        new AssessmentView(entityGateway, entityFactory, response, this.parentParentFrame);
    }

}
