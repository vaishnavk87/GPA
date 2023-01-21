package usecases.assessment.ViewAssessment;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;
import views.AssessmentView;

import javax.swing.*;

public class ViewAssessmentPresenter {

    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;
    private final JFrame frame;
    private final JFrame parentFrame;

    public ViewAssessmentPresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
        this.frame = frame;
        this.parentFrame = parentFrame;
    }

    public void present(ViewAssessmentResponse response) {
        new AssessmentView(entityGateway, entityFactory, response, parentFrame);
        frame.dispose();
    }

}
