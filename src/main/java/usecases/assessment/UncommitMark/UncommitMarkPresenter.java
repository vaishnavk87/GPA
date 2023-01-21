package usecases.assessment.UncommitMark;

import ports.database.EntityFactory;
import ports.database.EntityGateway;

import javax.swing.*;

public class UncommitMarkPresenter {
    private final JFrame frame;
    private final EntityGateway entityGateway;
    private final EntityFactory entityFactory;
    private final JFrame parentFrame;
    private final JFrame parentParentFrame;

    public UncommitMarkPresenter(JFrame frame, EntityGateway entityGateway, EntityFactory entityFactory, JFrame parentFrame, JFrame parentParentFrame) {
        this.frame = frame;
        this.entityGateway = entityGateway;
        this.entityFactory = entityFactory;
        this.parentFrame = parentFrame;
        this.parentParentFrame = parentParentFrame;
    }

    public void presentSuccess() {
        JOptionPane.showMessageDialog(frame, "Mark uncommitted successfully");
        parentFrame.dispose();
        parentParentFrame.dispose();
    }

    public void presentError(Throwable error) {
        JOptionPane.showMessageDialog(frame, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
