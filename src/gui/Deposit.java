package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener{
    JLabel accountbalanceLabel = new JLabel("Account balance:");
    JLabel accountBalance; // Will be initialized with the current balance from the database
    JLabel amountTobeDepositedLabel = new JLabel("Deposit:");
    JTextField amountTobeDeposited = new JTextField(3);
    private JLabel titleLabel;
    private JButton exitButton;
    Connection conn;

    public Deposit(String UserName) {
        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");

            // Set auto-commit to false to manage transactions manually
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Initialize account balance label with the current balance from the database
        accountBalance = new JLabel("Account balance: $" + getCurrentBalance(UserName));

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
        titleLabel= new JLabel("Deposit");
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


        JPanel mainPanel = new JPanel(new GridLayout(3, 2));
        JPanel buttonPanel = new JPanel();
        add(mainPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(accountbalanceLabel);
        mainPanel.add(accountBalance);
        mainPanel.add(amountTobeDepositedLabel);
        mainPanel.add(amountTobeDeposited);

        JButton depositButton = new JButton("Deposit");
        depositButton.setPreferredSize(new Dimension(400, 30));
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                depositMoney(UserName);
            }
        });
        buttonPanel.add(depositButton);
    }

    private double getCurrentBalance(String UserName) {
        double balance = 0;
        try {
            // Create and execute SQL query to retrieve balance for the given username
            String query = "SELECT Balance FROM users WHERE UserName = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, UserName);
            ResultSet rs = stmt.executeQuery();

            // If the result set has a record, get the balance value
            if (rs.next()) {
                balance = rs.getDouble("Balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    private void depositMoney(String UserName) {
        if (amountTobeDeposited.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input an amount to be deposited", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                double amount = Double.parseDouble(amountTobeDeposited.getText());

                // Get the current balance from the label
                String[] parts = accountBalance.getText().split("\\s+");
                double currentBalance = Double.parseDouble(parts[parts.length - 1].substring(1));
//
//                double currentBalance = Double.parseDouble(accountBalance.getText().substring(17)); // Extract the balance value
//                System.out.println(amount);

                // Calculate the new balance after deposit
                double newBalance = currentBalance + amount;

                // Update the balance label
                accountBalance.setText("Account balance: $" + newBalance);

                // Update the balance in the database
                updateBalanceInDatabase(UserName, newBalance);

                JOptionPane.showMessageDialog(this, "Deposited $" + amount);

                // Commit the transaction
                conn.commit();

                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while depositing money", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateBalanceInDatabase(String UserName, double newBalance) throws SQLException {
        // Create a prepared statement to update the balance in the database
        String query = "UPDATE users SET Balance = ? WHERE UserName = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDouble(1, newBalance);
        stmt.setString(2, UserName);
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            conn.commit(); // Commit the transaction if the update was successful
        } else {
            conn.rollback(); // Rollback the transaction if no rows were affected
        }
//        stmt.executeUpdate();
    }

    @Override
    public void dispose() {
        try {
            if (conn != null) {
                // Rollback the transaction if an error occurs
                conn.rollback();
                // Close the connection
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.dispose(); // Call the superclass method
       setVisible(false);
        new Home();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    System.out.println("view balance");    
    }
}
