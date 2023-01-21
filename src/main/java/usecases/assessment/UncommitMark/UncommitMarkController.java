package usecases.assessment.UncommitMark;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.uncommitMark.UncommitMarkRequest;

import javax.swing.*;

public class UncommitMarkController {

    public UncommitMarkController(UncommitMarkRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame, JFrame parentParentFrame) {
        UncommitMarkUseCase usecase = new UncommitMarkUseCase(entityGateway);
        UncommitMarkPresenter presenter = new UncommitMarkPresenter(frame, entityGateway, entityFactory, parentFrame, parentParentFrame);

        try {
            usecase.execute(request);
            presenter.presentSuccess();
        } catch (Throwable error) {
            presenter.presentError(error);
        }

    }
}
