package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
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
    private JPanel panel, Imgpanel;


    Connection conn;
    Statement st;

    public SignInPage() {
      
        setTitle("Login");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        Imgpanel = new JPanel();
        Imgpanel.setPreferredSize(new Dimension(400, 400));
   try {
            URL imageUrl = getClass().getResource("/images/logo.png"); // Corrected resource path
            if (imageUrl != null) {
                ImageIcon image = new ImageIcon(imageUrl);
                JLabel imageLabel = new JLabel(image);
                Imgpanel.add(imageLabel);
            } else {
                System.err.println("Image not found.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 60));
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");
        loginButton.setBackground(new Color(255, 50, 0));
        loginButton.setForeground(Color.white);
        signupButton.setBackground(new Color(255, 50, 0));
        signupButton.setForeground(Color.white);

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
      
        JPanel spacePanel = new JPanel();
        spacePanel.setPreferredSize(new Dimension(400, 10)); // Adjust the height as needed
        setLayout(new BorderLayout());
        add(Imgpanel, BorderLayout.NORTH);
        add(spacePanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
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
    public static void main(String[] args) {
        new SignInPage();
    }
}
