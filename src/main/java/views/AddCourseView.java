package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.addSemesterCourse.AddSemesterCourseRequest;
import usecases.account.AddSemesterCourse.AddSemesterCourseController;

import javax.swing.*;
import java.awt.*;

public class AddCourseView {
    public AddCourseView(EntityGateway entityGateway, EntityFactory entityFactory, String username, JFrame parentFrame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Add Course");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 245));

        // CourseCode label constructor
        JLabel label1 = new JLabel("Course Code");
        label1.setBounds(100, 10, 370, 20);
        panel.add(label1);

        // CourseCode text field constructor
        JTextField textField1 = new JTextField();
        textField1.setBounds(100, 30, 193, 28);
        panel.add(textField1);

        // CourseName label constructor
        JLabel label2 = new JLabel("Course Name");
        label2.setBounds(100, 60, 370, 20);
        panel.add(label2);

        // CourseTitle text field constructor
        JTextField textField2 = new JTextField();
        textField2.setBounds(100, 80, 193, 28);
        panel.add(textField2);

        // credit label constructor
        JLabel label3 = new JLabel("Credit");
        label3.setBounds(100, 110, 370, 20);
        panel.add(label3);

        // credit text field constructor
        JTextField textField3 = new JTextField();
        textField3.setBounds(100, 130, 193, 28);
        panel.add(textField3);

        // add course button
        JButton addCourseButton = new JButton("Add Course");
        addCourseButton.setBounds(100, 160, 193, 28);
        panel.add(addCourseButton);

        // back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(100, 180, 193, 28);
        panel.add(backButton);

        addCourseButton.addActionListener(e -> {
            String courseCode = textField1.getText();
            String courseName = textField2.getText();
            String credit = textField3.getText();

            AddSemesterCourseRequest request = new AddSemesterCourseRequest(username, courseCode, courseName, credit);
            new AddSemesterCourseController(request, frame, entityGateway, entityFactory, parentFrame);
        });

        backButton.addActionListener(e -> frame.dispose());

        frame.setVisible(true);
    }
}
