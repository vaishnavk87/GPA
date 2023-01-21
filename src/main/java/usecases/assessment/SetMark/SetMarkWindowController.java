package usecases.assessment.SetMark;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.setMark.SetMarkWindowRequest;
import ports.usecases.assessment.setMark.SetMarkWindowResponse;

import javax.swing.*;

public class SetMarkWindowController {

    public SetMarkWindowController(SetMarkWindowRequest request, JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        SetMarkWindowUseCase usecase = new SetMarkWindowUseCase(entityGateway);
        SetMarkWindowPresenter presenter = new SetMarkWindowPresenter(frame, entityGateway, entityFactory, parentFrame);

        SetMarkWindowResponse response = usecase.execute(request);
        presenter.present(response);


    }
}
