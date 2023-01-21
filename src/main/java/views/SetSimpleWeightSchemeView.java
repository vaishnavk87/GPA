package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.assessment.setWeightScheme.SetSimpleWeightSchemeRequest;
import usecases.assessment.SetSimpleWeightScheme.SetSimpleWeightSchemeController;

import javax.swing.*;
import java.awt.*;

public class SetSimpleWeightSchemeView {

    public SetSimpleWeightSchemeView(EntityGateway entityGateway, EntityFactory entityFactory, String username, String courseCode, String assessmentTitle, JFrame parentFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Set new weightscheme");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 245));

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
        JButton setSimpleWeightSchemeButton = new JButton("Set Weightscheme");
        setSimpleWeightSchemeButton.setBounds(100, 160, 193, 28);
        panel.add(setSimpleWeightSchemeButton);

        // back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 190, 193, 28);
        panel.add(backButton);

        setSimpleWeightSchemeButton.addActionListener(e -> {
            String numberOfInstances = textField2.getText();
            String weight = textField3.getText();

            SetSimpleWeightSchemeRequest request = new SetSimpleWeightSchemeRequest(username, courseCode, assessmentTitle, numberOfInstances, weight);
            new SetSimpleWeightSchemeController(request, frame, entityGateway, entityFactory, parentFrame);
        });


        backButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);

    }


}
