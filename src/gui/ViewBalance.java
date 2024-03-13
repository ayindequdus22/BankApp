package gui;
import gui.Home;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ViewBalance extends JFrame implements ActionListener{
public  static String username = null;
    private JLabel balanceLabel,titleLabel;
    private JButton exitButton;
    public ViewBalance() {
        setUndecorated(true);
        setSize(400, 300);
        setLocation(500, 250);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      JPanel navBar = new JPanel();
      navBar.setBackground(new Color(255, 250, 250));
        navBar.setBorder(new EmptyBorder(5, 10, 5, 10));
        navBar.setLayout(new BorderLayout());
        titleLabel= new JLabel("View Balance");
        exitButton = new JButton("Back");
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(255, 50, 0));
        exitButton.setFocusPainted(false);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        navBar.add(titleLabel, BorderLayout.WEST);
        navBar.add(exitButton, BorderLayout.EAST);






        JPanel mainPanel = new JPanel(new BorderLayout());
        add(navBar, BorderLayout.NORTH);
        add(mainPanel);

        balanceLabel = new JLabel("Your balance is: " );
        mainPanel.add(balanceLabel, BorderLayout.CENTER);

        // Update the balance label with the balance retrieved from the database
        updateBalanceLabel(username);
    }

    private void updateBalanceLabel(String UserName) {
        try {
            // Establish connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");

            // Create and execute SQL query to retrieve balance for the given username
            String query = "SELECT Balance FROM users WHERE UserName = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, UserName);
            ResultSet rs = stmt.executeQuery();

            // If the result set has a record, get the balance value
            if (rs.next()) {
                double Balance = rs.getDouble("Balance");
                balanceLabel.setText("Your balance is: $" + Balance);
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dispose() {
        super.dispose();setVisible(false);
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    System.out.println("view balance");    
    }
}
