package usecases.assessment.SetMark;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.setMark.SetMarkRequest;
import ports.usecases.assessment.viewAssessment.ViewAssessmentResponse;

import javax.swing.*;

public class SetMarkController {
    public boolean isSuccessful;

    public SetMarkController(SetMarkRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame, JFrame parentParentFrame) {
        SetMarkUseCase usecase = new SetMarkUseCase(entityGateway);
        SetMarkPresenter presenter = new SetMarkPresenter(frame, entityGateway, entityFactory, parentFrame, parentParentFrame);

        try {
            ViewAssessmentResponse response = usecase.execute(request);
            presenter.presentSuccess(response);
            isSuccessful = true;
        } catch (Throwable error) {
            presenter.presentError(error);
            isSuccessful = false;
        }
    }
}
