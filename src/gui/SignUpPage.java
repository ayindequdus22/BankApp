package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPage extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton SignupButton, loginButton;
    private JLabel usernameLabel, passwordLabel;
    private JPanel panel;

    public SignUpPage() {
        setTitle("Signup");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        setResizable(false);
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        SignupButton = new JButton("Signup");
        loginButton = new JButton("Login");

        SignupButton.addActionListener(this);
        loginButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(SignupButton);
        panel.add(loginButton);
        setVisible(true);
        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SignupButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Perform Signup validation (not implemented in this basic example)
            if (validateSignup(username, password)) {
                new Home();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password");
            }
        } else if (e.getSource() == loginButton) {
            new SignInPage();
        }
    }
    private boolean validateSignup(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

  
}
