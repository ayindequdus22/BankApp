package gui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Profile extends JFrame implements ActionListener{
    private JButton exitButton;
private JLabel titleLabel;
        public Profile(String UserName) {
       setVisible(true);

        setUndecorated(true);
        setSize(400, 300);
        setLocation(500, 250);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     JPanel navBar = new JPanel();
        navBar.setBackground(new Color(255, 216, 230));
        navBar.setBorder(new EmptyBorder(5, 10, 5, 10));
        navBar.setLayout(new BorderLayout());
        titleLabel= new JLabel("Profile");
        exitButton = new JButton("Back");
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        navBar.add(titleLabel, BorderLayout.WEST);
        navBar.add(exitButton, BorderLayout.EAST);

        add(navBar);
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));

        // Get user data from the database
        String[] userData = getUserDataFromDatabase(UserName);

        // Populate the labels with user data
        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(new JLabel(userData[0])); // Display user's name
        mainPanel.add(new JLabel("Email:"));
        mainPanel.add(new JLabel(userData[1]));
        mainPanel.add(new JLabel("Account Number:"));
        mainPanel.add(new JLabel(userData[2])); // Display user's account number
        mainPanel.add(new JLabel("Balance:"));
        mainPanel.add(new JLabel(userData[3])); // Display user's balance

        add(mainPanel);

        setVisible(true);
        setResizable(false);
    }

    @Override
    public void dispose() {
        super.dispose();
        new Home(); // Open the home page when the profile page is closed
    }

    private String[] getUserDataFromDatabase(String UserName) {
        String[] userData = new String[4];
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");

            // Create and execute SQL query to retrieve user data
            String query = "SELECT FirstName, LastName, Email, AccountNumber, Balance FROM users WHERE UserName = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, UserName);
            rs = stmt.executeQuery();

            // If the result set has a record, retrieve user data
            if (rs.next()) {
                String FirstName = rs.getString("FirstName"); // Get user's first name
                String LastName = rs.getString("LastName"); // Get user's last name
                userData[0] = FirstName + " " + LastName; // Concatenate first name and last name
                userData[1] = rs.getString("Email"); // Get user's username
                userData[2] = rs.getString("AccountNumber"); // Get user's account number
                userData[3] = "$" + rs.getDouble("Balance"); // Get user's balance

                // Print retrieved data for debugging
                System.out.println("First Name: " + FirstName);
                System.out.println("Last Name: " + LastName);
                System.out.println("Email: " + userData[1]);
                System.out.println("Account Number: " + userData[2]);
                System.out.println("Balance: " + userData[3]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return userData;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
System.out.println("Profile");    }

}
