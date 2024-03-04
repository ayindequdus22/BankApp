package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SignInPage extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, signupButton;
    private JLabel usernameLabel, passwordLabel;
    private JPanel panel;

    Connection conn;
    Statement st;

    public SignInPage() {

        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 250);
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
        // Add action listener to the signupButton
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the sign-up page when signupButton is clicked
                new SignUpPage().setVisible(true);
                dispose(); // Close the current window
            }
        });
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(signupButton);
        setVisible(true);
        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String UserName = usernameField.getText();
            String Password = new String(passwordField.getPassword());

            if (!UserName.isEmpty() && !Password.isEmpty()) {
                // All fields are filled
                loadSql();
                try {
                    String query = "SELECT * FROM users WHERE UserName='" + UserName + "' AND Password='" + Password + "'";
                    ResultSet rs = st.executeQuery(query);
                    if (rs.next()) {
                        // User found, proceed to next page
                        dispose();
                        setVisible(false);
                        new Home();
//                        JOptionPane.showMessageDialog(null, "Login Successful!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid username or password");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "All fields are required");
            }
        }
    }

    public void loadSql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");
            st = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
