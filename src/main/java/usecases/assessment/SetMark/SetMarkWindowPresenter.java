package usecases.assessment.SetMark;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.setMark.SetMarkWindowResponse;
import views.SetMarkView;

import javax.swing.*;

public class SetMarkWindowPresenter {
    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;
    private final JFrame frame;
    private final JFrame parentFrame;

    public SetMarkWindowPresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame) {
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
        this.frame = frame;
        this.parentFrame = parentFrame;
    }

    public void present(SetMarkWindowResponse response) {
        new SetMarkView(entityGateway, entityFactory, response, frame, parentFrame);
        frame.dispose();
    }

    }

