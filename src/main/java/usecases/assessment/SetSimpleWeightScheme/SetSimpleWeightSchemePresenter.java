package usecases.assessment.SetSimpleWeightScheme;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.setWeightScheme.SetSimpleWeightSchemeResponse;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;
import views.AssessmentView;

import javax.swing.*;

public class SetSimpleWeightSchemePresenter {

    private final JFrame frame;
    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;
    private final JFrame parentFrame;

    public SetSimpleWeightSchemePresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
        this.frame = frame;
        this.parentFrame = parentFrame;
    }

    public void presentError(Throwable error) {
        JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void presentSuccess(ViewAssessmentResponse response) {
        JOptionPane.showMessageDialog(null, "Weight Scheme Set", "Success", JOptionPane.INFORMATION_MESSAGE);
        this.frame.dispose();
        this.parentFrame.dispose();
        new AssessmentView(entityGateway, entityFactory, response, parentFrame);
    }
}
