package views;

import ports.database.EntityFactory;
import ports.database.EntityGateway;
import ports.usecases.account.addAccount.AddAccountRequest;
import usecases.account.AddAccount.AddAccountController;

import javax.swing.*;
import java.awt.*;

public class AddAccountView {
    public AddAccountView(EntityGateway entityGateway, EntityFactory entityFactory) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // JFrame class
        JFrame frame = new JFrame();
        frame.setTitle("Just Bob: Register");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 260));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Username label constructor
        JLabel label1 = new JLabel("Username");
        label1.setBounds(100, 8, 193, 20);
        panel.add(label1);

        // Username TextField constructor
        JTextField username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        panel.add(username);

        // Password Label constructor
        JLabel label2 = new JLabel("Password");
        label2.setBounds(100, 55, 193, 20);
        panel.add(label2);

        // Password TextField
        JPasswordField password = new JPasswordField();
        password.setBounds(100, 75, 193, 28);
        panel.add(password);

        // Repeat Password Label constructor
        JLabel label3 = new JLabel("Repeat Password");
        label3.setBounds(100, 105, 193, 20);
        panel.add(label3);

        // Repeat Password TextField
        JPasswordField repeatPassword = new JPasswordField();
        repeatPassword.setBounds(100, 125, 193, 28);
        panel.add(repeatPassword);

        // Register Button constructor
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 160, 193, 28);
        panel.add(registerButton);

        // Login Button constructor
        JButton loginPageButton = new JButton("Login");
        loginPageButton.setBounds(100, 190, 193, 28);
        panel.add(loginPageButton);

        registerButton.addActionListener(e -> {
            String inputUsername = username.getText();
            String inputPassword = new String(password.getPassword());
            String inputRepeatPassword = new String(repeatPassword.getPassword());

            AddAccountRequest request = new AddAccountRequest(inputUsername, inputPassword, inputRepeatPassword);
            new AddAccountController(request, frame, entityGateway, entityFactory);
        });

        loginPageButton.addActionListener(e -> {
            frame.dispose();
            new LoginView(entityGateway, entityFactory);
        });

        frame.setVisible(true);
    }
}
