package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.viewAccount.ViewAccountResponse;
import ports.usecases.account.viewSemester.ViewSemesterRequest;
import usecases.account.ViewSemester.ViewSemesterController;

import javax.swing.*;
import java.awt.*;

public class AccountView {

    public final int WIDTH = 600;
    public final int HEIGHT = 600;

    public AccountView(EntityGateway entityGateway, EntityFactory entityFactory, ViewAccountResponse response) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("My Account");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton semesterButton = new JButton("Current Semester");
        semesterButton.setBounds(WIDTH / 3, HEIGHT / 3 - 25, 193, 28);
        panel.add(semesterButton);

        JButton archiveButton = new JButton("Archive");
        archiveButton.setBounds(WIDTH / 3, semesterButton.getY() + 25, 193, 28);
        panel.add(archiveButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(WIDTH / 3, archiveButton.getY() + 25, 193, 28);
        panel.add(logoutButton);

        semesterButton.addActionListener((e ->{
                ViewSemesterRequest request = new ViewSemesterRequest(response.username, response.semesterTitle);
                new ViewSemesterController(request, frame, entityGateway, entityFactory, frame);
                }
        ));


        frame.setVisible(true);
    }
}
