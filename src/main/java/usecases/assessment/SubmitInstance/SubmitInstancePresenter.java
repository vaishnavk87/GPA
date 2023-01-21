package usecases.assessment.SubmitInstance;

import ports.database.EntityGateway;
import ports.usecases.assessment.submitInstance.SubmitInstanceResponse;

import javax.swing.*;

public class SubmitInstancePresenter {
    private final JFrame frame;
    private final EntityGateway entityGateway;
    private final JFrame parentFrame;

    public SubmitInstancePresenter(JFrame frame, EntityGateway entityGateway, JFrame parentFrame) {
        this.entityGateway = entityGateway;
        this.frame = frame;
        this.parentFrame = parentFrame;
    }

    public void presentError(Throwable error) {
        JOptionPane.showMessageDialog(null, error.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void presentSuccess(SubmitInstanceResponse response) {
        if (response.SubmitSuccessful) {
            JOptionPane.showMessageDialog(null, "Assessment Submitted", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.frame.dispose();
            this.parentFrame.dispose();
        }
    }
}
