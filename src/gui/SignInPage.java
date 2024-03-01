package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SignInPage extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;
    private JLabel usernameLabel, passwordLabel;
    private JPanel panel;

    public SignInPage() {
        setTitle("Login");
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

        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");

        loginButton.addActionListener(this);
        signupButton.addActionListener(this);

        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Perform SignInPage validation (not implemented in this basic example)
            if (validateSignInPage(username, password)) {
                new Home();
                // JOptionPane.showMessageDialog(this, "SignInPage Successful");
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Username or -Password");
            }
        } else if (e.getSource() == signupButton) {
            // Here you can open a new window for signup or handle signup process
            new SignUpPage();
            // JOptionPane.showMessageDialog(this, "Signup feature not implemented yet");
        }
    }

    // Placeholder method for SignInPage validation
    private boolean validateSignInPage(String username, String password) {
        // In a real application, you would validate the username and password against a database or other storage
        // Here, let's just have a dummy validation
        return username.equals("admin") && password.equals("admin123");
    }

}
