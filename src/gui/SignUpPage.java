package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.net.URL;

public class SignUpPage extends JFrame implements ActionListener {

    private JTextField FirstNameField, LastNameField, UserNameField, EmailField;
    private JPasswordField PasswordField;
    private JButton submitBtn, loginButton;
    private JLabel FirstNameLabel, LastNameLabel, UserNameLabel, PasswordLabel, EmailLabel;
    private JPanel panel, Imgpanel,buttonPanel;

    Connection conn;
    Statement st;

    public SignUpPage() {
        setTitle("Signup");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
      
        panel = new JPanel();
        buttonPanel  = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        // panel.setBackground(new Color(255, 165, 0)); 
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
        submitBtn = new JButton("Submit");
        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(255, 50, 0));
        loginButton.setForeground(Color.white);
        submitBtn.setBackground(new Color(255, 50, 0));
        submitBtn.setForeground(Color.white);
        loginButton.setBackground(new Color(255, 50, 0));
        submitBtn.addActionListener(this);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignInPage().setVisible(true);
                dispose();
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
        
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); // Set BoxLayout with Y_AXIS alignment

        // Add rigid area to create space between buttons
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Adjust the height as needed
        
        // Add buttons with centered alignment
        submitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitBtn.setPreferredSize(new Dimension(200, 40));
        loginButton.setPreferredSize(new Dimension(200, 40));
        // Add buttons to buttonPanel
        buttonPanel.add(submitBtn);
        buttonPanel.add(loginButton);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        setLayout(new BorderLayout());
        // Use BorderLayout to properly position panels
        setLayout(new BorderLayout());
        add(Imgpanel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String FirstName = FirstNameField.getText();
            String LastName = LastNameField.getText();
            String UserName = UserNameField.getText();
            String Email = EmailField.getText();
            String Password = new String(PasswordField.getPassword());

            if (!FirstName.isEmpty() && !LastName.isEmpty() && !UserName.isEmpty() && !Password.isEmpty()
                    && !Email.isEmpty()) {
                if (Password.length() >= 3 && Email.endsWith("@gmail.com") && Email.length() >= 3) {
                    loadSql();
                    try {
                        String AccountNumber = generateAccountNumber();
                        String query = "INSERT INTO users (FirstName, LastName, UserName, Email, Password, AccountNumber) VALUES('"
                                + FirstName + "','" + LastName + "','" + UserName + "','" + Email + "','" + Password
                                + "','" + AccountNumber + "')";
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

    private void loadSql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");
            st = conn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder AccountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            AccountNumber.append(random.nextInt(10));
        }
        return AccountNumber.toString();
    }
}

