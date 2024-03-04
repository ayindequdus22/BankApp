package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignUpPage extends JFrame implements ActionListener {

    private JTextField usernameField,firstNameField,secondNameField,emailField;
    private JPasswordField passwordField;
    private JButton SignupButton, loginButton;
    private JLabel firstNameLabel,secondNameLabel,usernameLabel, passwordLabel,emaiLabel;
    private JPanel panel;

    public SignUpPage() {
        setTitle("Signup");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);
        setResizable(false);
        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        firstNameLabel = new JLabel("First Name:");
        secondNameLabel = new JLabel("Last Name:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        emaiLabel = new JLabel("E-mail:");

        firstNameField = new JTextField();
        secondNameField = new JPasswordField();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        emailField  =  new JTextField();
        SignupButton = new JButton("Signup");
        loginButton = new JButton("Login");

        SignupButton.addActionListener(this);
        loginButton.addActionListener(this);


        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(secondNameLabel);
        panel.add(secondNameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(emaiLabel);
        panel.add(emailField);   
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
            setVisible(false);
            new SignInPage();
        }
    }
    private boolean validateSignup(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }
public static void main(String[] args) {
    new SignUpPage();
}
  
}