package usecases.assessment.SubmitInstance;

import ports.database.EntityGateway;
import ports.usecases.assessment.submitInstance.SubmitInstanceRequest;
import ports.usecases.assessment.submitInstance.SubmitInstanceResponse;

import javax.swing.*;

public class SubmitInstanceController {
    public boolean isSuccessful;
    public SubmitInstanceController(SubmitInstanceRequest request, JFrame frame, EntityGateway entityGateway, JFrame parentFrame) {
        SubmitInstanceUseCase useCase = new SubmitInstanceUseCase(entityGateway);
        SubmitInstancePresenter presenter = new SubmitInstancePresenter(frame, entityGateway, parentFrame);

        try {
            SubmitInstanceResponse response = useCase.execute(request);
            presenter.presentSuccess(response);
            isSuccessful = true;
        } catch (Throwable error) {
            presenter.presentError(error);
            isSuccessful = false;
        }
    }
}
