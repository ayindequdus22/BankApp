package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Withdrawal extends JFrame implements ActionListener {
    private JLabel amountLabel,titleLabel;
    private JTextField amountField;
    private JButton withdrawButton,exitButton;
    private String userName; // Variable to store the username

    public Withdrawal(String UserName) {
        this.userName = UserName; // Initialize the username variable
        amountLabel = new JLabel("Enter Withdrawal Amount:");
        amountField = new JTextField(10);
        withdrawButton = new JButton("Withdraw");

        withdrawButton.addActionListener(this);
        setUndecorated(true);
        setSize(400, 300);
        setLocation(500, 250);
        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel navBar = new JPanel();
        navBar.setBackground(new Color(255, 250, 250));
        navBar.setBorder(new EmptyBorder(5, 10, 5, 10));
        navBar.setLayout(new BorderLayout());
        titleLabel= new JLabel("Withdrawal");
        exitButton = new JButton("Back");
        exitButton.setForeground(Color.WHITE);
        exitButton.setFocusPainted(false);
        exitButton.setBackground(new Color(255, 50, 0));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        navBar.add(titleLabel, BorderLayout.WEST);
        navBar.add(exitButton, BorderLayout.EAST);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(amountLabel);
        mainPanel.add(amountField);

        JPanel btnPanel = new JPanel();
        btnPanel.add(withdrawButton);
        add(navBar,BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        add(btnPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    @Override
    public void dispose() {
        super.dispose(); // Call the superclass method
        new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            String amountString = amountField.getText();
            try {
                double amount = Double.parseDouble(amountString);
                double currentBalance = getCurrentBalanceFromDatabase();
                if (currentBalance < amount) {
                    JOptionPane.showMessageDialog(this, "Withdrawal not possible, either deposit or reduce the amount to be withdrawn");
                } else {
                    performWithdrawal(amount);
                    JOptionPane.showMessageDialog(this, "Withdrawal successful!");
                    dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error occurred while processing withdrawal.");
            }
        }
    }

    private double getCurrentBalanceFromDatabase() throws SQLException {
        double currentBalance = 0;
        // Retrieve the current balance from the database
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");
            String query = "SELECT Balance FROM users WHERE UserName = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, userName); // Use the username variable
            rs = stmt.executeQuery();
            if (rs.next()) {
                currentBalance = rs.getDouble("Balance");
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
        return currentBalance;
    }

    private void performWithdrawal(double amount) throws SQLException {
        // Update the balance in the database after withdrawal
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");
            double currentBalance = getCurrentBalanceFromDatabase();
            double newBalance = currentBalance - amount;
            String query = "UPDATE users SET Balance = ? WHERE UserName = ?";
            stmt = conn.prepareStatement(query);
            stmt.setDouble(1, newBalance);
            stmt.setString(2, userName); // Use the username variable
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }
    }
}
