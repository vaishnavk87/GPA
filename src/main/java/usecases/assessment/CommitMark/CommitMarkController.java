package usecases.assessment.CommitMark;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.commitMark.CommitMarkRequest;

import javax.swing.*;

public class CommitMarkController {
    public CommitMarkController(CommitMarkRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame, JFrame parentParentFrame) {
        CommitMarkUseCase usecase = new CommitMarkUseCase(entityGateway);
        CommitMarkPresenter presenter = new CommitMarkPresenter(frame, entityGateway, entityFactory, parentFrame, parentParentFrame);

        try {
            usecase.execute(request);
            presenter.presentSuccess();
        } catch (Throwable error) {
            presenter.presentError(error);
        }

    }

}
