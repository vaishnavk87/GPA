package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.commitMark.CommitMarkRequest;
import ports.usecases.assessment.setMark.SetMarkRequest;
import ports.usecases.assessment.setMark.SetMarkWindowResponse;
import ports.usecases.assessment.submitInstance.SubmitInstanceRequest;
import ports.usecases.assessment.uncommitMark.UncommitMarkRequest;
import usecases.assessment.CommitMark.CommitMarkController;
import usecases.assessment.SetMark.SetMarkController;
import usecases.assessment.SubmitInstance.SubmitInstanceController;
import usecases.assessment.UncommitMark.UncommitMarkController;

import javax.swing.*;
import java.awt.*;

public class SetMarkView {

    public SetMarkView(EntityGateway entityGateway, EntityFactory entityFactory, SetMarkWindowResponse response, JFrame parentFrame, JFrame parentParentFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Mark of " + response.instanceName);
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Mark Label constructor
        JLabel label1 = new JLabel("Enter mark:");
        label1.setBounds(100, 8, 193, 20);
        panel.add(label1);

        // Mark textfield
        JTextField mark = new JTextField();
        mark.setBounds(100, 27, 193, 28);
        panel.add(mark);

        // Set Mark Button constructor
        JButton setMarkButton = new JButton("Set Mark");
        setMarkButton.setBounds(100, 100, 193, 28);
        panel.add(setMarkButton);

        // Commit Mark Button constructor
        JButton commitMarkButton = new JButton("Commit Mark");
        commitMarkButton.setBounds(100, 140, 193, 28);
        panel.add(commitMarkButton);

        // Uncommit Mark Button Constructor
        JButton uncommitMarkButton = new JButton("Uncommit Mark");
        uncommitMarkButton.setBounds(100, 180, 193, 28);
        panel.add(uncommitMarkButton);

        // back button constructor
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 220, 193, 28);
        panel.add(backButton);


        setMarkButton.addActionListener(e -> {
            SetMarkRequest setMarkRequest = new SetMarkRequest(response.username, response.courseCode, response.assessmentTitle, response.instanceNumber, mark.getText());
            new SetMarkController(setMarkRequest, frame, entityGateway, entityFactory, parentFrame, parentParentFrame);
        });


        commitMarkButton.addActionListener(e -> {
            SubmitInstanceRequest submitInstanceRequest = new SubmitInstanceRequest(response.username, response.courseCode, response.assessmentTitle, response.instanceNumber);
            SubmitInstanceController submitInstanceController = new SubmitInstanceController(submitInstanceRequest, frame, entityGateway, parentFrame);
            if (submitInstanceController.isSuccessful) {
                SetMarkRequest request = new SetMarkRequest(response.username, response.courseCode, response.assessmentTitle, response.instanceNumber, mark.getText());
                SetMarkController setMarkController = new SetMarkController(request, frame, entityGateway, entityFactory, parentFrame, parentParentFrame);
                if (setMarkController.isSuccessful) {
                    CommitMarkRequest commitMarkRequest = new CommitMarkRequest(response.username, response.courseCode, response.assessmentTitle, response.instanceNumber);
                    new CommitMarkController(commitMarkRequest, frame, entityGateway, entityFactory, parentFrame, parentParentFrame);
                }
            }

        });

        uncommitMarkButton.addActionListener(e -> {
            UncommitMarkRequest uncommitMarkRequest = new UncommitMarkRequest(response.username, response.courseCode, response.assessmentTitle, response.instanceNumber);
            new UncommitMarkController(uncommitMarkRequest, frame, entityGateway, entityFactory, parentFrame, parentParentFrame);
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            parentFrame.setVisible(true);
        });

        frame.setVisible(true);
    }

}
