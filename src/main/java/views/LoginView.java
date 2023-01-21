package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.loginAccount.LoginAccountRequest;
import usecases.account.LoginAccount.LoginAccountController;

import javax.swing.*;
import java.awt.*;

public class LoginView {

    public final int WIDTH = 600;
    public final int HEIGHT = 600;

    public LoginView(EntityGateway entityGateway, EntityFactory entityFactory) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Just Bob: Login");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username label constructor
        JLabel label1 = new JLabel("Username");
        label1.setBounds(WIDTH / 3, HEIGHT / 3 - 25, 70, 20);
        panel.add(label1);

        // Username TextField constructor
        JTextField username = new JTextField();
        username.setBounds(label1.getX(), label1.getY() + 15, 193, 28);
        panel.add(username);

        // Password Label constructor
        JLabel label2 = new JLabel("Password");
        label2.setBounds(label1.getX(), username.getY() + 25, 193, 20);
        panel.add(label2);

        // Password TextField
        JPasswordField password = new JPasswordField();
        password.setBounds(label1.getX(), label2.getY() + 15, 193, 28);
        panel.add(password);

        // Login Button constructor
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(label1.getX(), password.getY() + 40, 193, 28);
        panel.add(loginButton);

        // Register Button constructor
        JButton registerPageButton = new JButton("Register Account");
        registerPageButton.setBounds(label1.getX(), loginButton.getY() + 25, 193, 28);
        panel.add(registerPageButton);

        loginButton.addActionListener(e -> {
            String inputUsername = username.getText();
            String inputPassword = new String(password.getPassword());

            LoginAccountRequest request = new LoginAccountRequest(inputUsername, inputPassword);
            new LoginAccountController(request, frame, entityGateway, entityFactory);
        });

        registerPageButton.addActionListener(e -> {
            frame.dispose();
            new AddAccountView(entityGateway, entityFactory);
        });

        frame.setVisible(true);
    }
}
