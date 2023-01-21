package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.addSimpleAssessment.AddSimpleAssessmentRequest;
import usecases.assessment.AddSimpleAssessment.AddSimpleAssessmentController;

import javax.swing.*;
import java.awt.*;

public class AddSimpleAssessmentView {

    public AddSimpleAssessmentView(EntityGateway entityGateway, EntityFactory entityFactory, String username, String courseCode, JFrame parentFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Add Assessment Group");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 245));

        // Assessment Title label constructor
        JLabel label1 = new JLabel("Assessment Title");
        label1.setBounds(100, 10, 370, 20);
        panel.add(label1);

        // Assessment Title text field constructor
        JTextField textField1 = new JTextField();
        textField1.setBounds(100, 30, 193, 28);
        panel.add(textField1);

        // Assessment Number Of Instances label constructor
        JLabel label2 = new JLabel("Number Of Assessment Instances");
        label2.setBounds(100, 60, 370, 20);
        panel.add(label2);

        // Assessment Number Of Instances text field constructor
        JTextField textField2 = new JTextField();
        textField2.setBounds(100, 80, 193, 28);
        panel.add(textField2);

        // Assessment Weight label constructor
        JLabel label3 = new JLabel("Weight of each Assessment Instance");
        label3.setBounds(100, 110, 370, 20);
        panel.add(label3);

        // Assessment Weight text field constructor
        JTextField textField3 = new JTextField();
        textField3.setBounds(100, 130, 193, 28);
        panel.add(textField3);

        // add assessment button
        JButton addSimpleAssessmentButton = new JButton("Add Assessment");
        addSimpleAssessmentButton.setBounds(100, 160, 193, 28);
        panel.add(addSimpleAssessmentButton);

        // back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 190, 193, 28);
        panel.add(backButton);

        addSimpleAssessmentButton.addActionListener(e -> {
            String assessmentTitle = textField1.getText();
            String numberOfInstances = textField2.getText();
            String weight = textField3.getText();

            AddSimpleAssessmentRequest request = new AddSimpleAssessmentRequest(username, courseCode, assessmentTitle, numberOfInstances, weight);
            new AddSimpleAssessmentController(request, frame, entityGateway, entityFactory, parentFrame);
        });

        backButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
