package usecases.assessment.ViewAssessment;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.viewAssessment.ViewAssessmentRequest;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;

import javax.swing.*;

public class ViewAssessmentController {

    public ViewAssessmentController(ViewAssessmentRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        ViewAssessmentUseCase usecase = new ViewAssessmentUseCase(entityGateway);
        ViewAssessmentPresenter presenter = new ViewAssessmentPresenter(frame, entityGateway, entityFactory, parentFrame);

        ViewAssessmentResponse response = usecase.execute(request);
        presenter.present(response);
    }
}
