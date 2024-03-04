package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SignUpPage extends JFrame implements ActionListener {

    private JTextField FirstNameField, LastNameField, UserNameField, EmailField;
    private JPasswordField PasswordField;
    private JButton signupButton, loginButton;
    private JLabel FirstNameLabel, LastNameLabel, UserNameLabel, PasswordLabel, EmailLabel;
    private JPanel panel;

    Connection conn;
    Statement st;

    public SignUpPage() {

        setTitle("Signup");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500, 250);
        setResizable(false);
        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));

        FirstNameLabel = new JLabel("First Name:");
        LastNameLabel = new JLabel("Last Name:");
        UserNameLabel = new JLabel("Username:");
        PasswordLabel = new JLabel("Password:");
        EmailLabel = new JLabel("E-mail:");

        FirstNameField = new JTextField();
        LastNameField = new JTextField();
        UserNameField = new JTextField();
        PasswordField = new JPasswordField();
        EmailField = new JTextField();
        signupButton = new JButton("Signup");
        loginButton = new JButton("Login");

        signupButton.addActionListener(this);
        // Add action listener to the signupButton
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the sign-up page when signinButton is clicked
                new SignInPage().setVisible(true);
                dispose(); // Close the current window
            }
        });
        panel.add(FirstNameLabel);
        panel.add(FirstNameField);
        panel.add(LastNameLabel);
        panel.add(LastNameField);
        panel.add(UserNameLabel);
        panel.add(UserNameField);
        panel.add(EmailLabel);
        panel.add(EmailField);
        panel.add(PasswordLabel);
        panel.add(PasswordField);
        panel.add(signupButton);
        panel.add(loginButton);
        setVisible(true);
        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String FirstName = FirstNameField.getText();
            String LastName = LastNameField.getText();
            String UserName = UserNameField.getText();
            String Email = EmailField.getText();
            String Password = new String(PasswordField.getPassword());

            if (!FirstName.isEmpty() && !LastName.isEmpty() && !UserName.isEmpty() && !Password.isEmpty() && !Email.isEmpty()) {
                // All fields are filled
                if (Password.length() >= 3 && Email.endsWith("@gmail.com") && Email.length() >= 3) {
                    // Validations passed, proceed to database insertion
                    loadSql();
                    try {
                        String query = "INSERT INTO users (FirstName, LastName, UserName, Email, Password) VALUES('" + FirstName + "','" + LastName + "','" + UserName + "','" + Email + "','" + Password + "')";
                        st.execute(query);
                        dispose();
                        setVisible(false);
                        new SignInPage();
                        JOptionPane.showMessageDialog(null, "Successfully inserted values");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid password or email");
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
