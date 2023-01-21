package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.submitInstance.SubmitInstanceRequest;
import usecases.assessment.SubmitInstance.SubmitInstanceController;

import javax.swing.*;
import java.awt.*;

public class SubmitInstanceView {

    public SubmitInstanceView(EntityGateway entityGateway, EntityFactory entityFactory, String userName, JFrame parentFrame){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);

        frame.setSize(400, 300);
        frame.add(panel);
        frame.setVisible(true);

        Font textFont = new Font("Georgia", Font.PLAIN, 14);

        JLabel title = new JLabel("Submit Assessment");
        title.setBounds(100, 0, 200, 25);
        title.setFont(new Font("Georgia", Font.BOLD, 16));
        panel.add(title);

        JLabel courseCode = new JLabel("Course Code:");
        courseCode.setBounds(10, 40, 150, 25);
        courseCode.setFont(textFont);
        panel.add(courseCode);

        JLabel assessmentTitle = new JLabel("Assessment Title:");
        assessmentTitle.setBounds(10, 70, 150, 25);
        assessmentTitle.setFont(textFont);
        panel.add(assessmentTitle);

        JLabel instanceNumber = new JLabel("Instance Number:");
        instanceNumber.setBounds(10, 100, 150, 25);
        instanceNumber.setFont(textFont);
        panel.add(instanceNumber);

        JTextField courseCodeField = new JTextField();
        courseCodeField.setBounds(160, 40, 170, 25);
        courseCodeField.setFont(textFont);
        panel.add(courseCodeField);

        JTextField assessmentTitleField = new JTextField();
        assessmentTitleField.setBounds(160, 70, 170, 25);
        assessmentTitleField.setFont(textFont);
        panel.add(assessmentTitleField);

        JTextField instanceNumberField = new JTextField();
        instanceNumberField.setBounds(160, 100, 170, 25);
        instanceNumberField.setFont(textFont);
        panel.add(instanceNumberField);

        JButton submit = new JButton("Submit");
        submit.setBounds(10, 150, 100, 25);
        submit.setFont(textFont);
        panel.add(submit);

        submit.addActionListener(e -> {
            if (courseCodeField.getText() == null || assessmentTitleField.getText() == null
                    || instanceNumberField.getText() == null){
                JOptionPane.showMessageDialog(null,
                        "Please enter all course code, assessment tile, and instance number",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            String theCourseCode = courseCodeField.getText();
            String theAssessmentTitle = assessmentTitleField.getText();
            int theInstanceNumber = Integer.parseInt(instanceNumberField.getText());
            SubmitInstanceRequest request = new SubmitInstanceRequest(userName, theCourseCode,
                    theAssessmentTitle, theInstanceNumber);
            new SubmitInstanceController(request, frame, entityGateway, parentFrame);
        });
    }
}
