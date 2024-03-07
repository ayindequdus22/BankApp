package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Transfer extends JFrame {
    private JTextField toAccountField;
    private JTextField amountField;
    private JButton transferButton;
    private String UserName;

    public Transfer(String UserName) {
        this.UserName = UserName;
        setTitle("Transfer");
        setSize(400, 200);
        setLocation(500, 250);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        JPanel buttonPanel = new JPanel();
        add(mainPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        JLabel toLabel = new JLabel("To Account:");
        toAccountField = new JTextField(10);
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);
        transferButton = new JButton("Transfer");

        mainPanel.add(toLabel);
        mainPanel.add(toAccountField);
        mainPanel.add(amountLabel);
        mainPanel.add(amountField);
        transferButton.setPreferredSize(new Dimension(400, 30));

        buttonPanel.add(transferButton);

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transferMoney();
            }
        });
    }

    private void transferMoney() {
        String toAccount = toAccountField.getText();
        String amountText = amountField.getText();

        if (toAccount.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (toAccount.length() != 10) {
            JOptionPane.showMessageDialog(this, "Account number must be 10 digits long", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                double amount = Double.parseDouble(amountText);
                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Amount must be greater than zero", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Connect to the database
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");
                // Retrieve the current balance
                double currentBalance = getCurrentBalance(UserName, conn);
                // Check if the balance is sufficient for the transfer
                if (currentBalance < amount) {
                    JOptionPane.showMessageDialog(this, "Insufficient balance", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Update the balance after transfer
                double newBalance = currentBalance - amount;
                updateBalance(UserName, newBalance, conn);
                // Close the database connection
                conn.close();

                JOptionPane.showMessageDialog(this, "Transfer successful. New balance: $" + newBalance, "Success", JOptionPane.INFORMATION_MESSAGE);
                toAccountField.setText("");
                amountField.setText("");
                dispose();
            } catch (NumberFormatException | SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double getCurrentBalance(String UserName, Connection conn) throws SQLException {
        double Balance = 0;
        String query = "SELECT balance FROM users WHERE UserName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, UserName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Balance = rs.getDouble("balance");
            }
        }
        return Balance;
    }

    private void updateBalance(String UserName, double newBalance, Connection conn) throws SQLException {
        String query = "UPDATE users SET balance = ? WHERE UserName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, newBalance);
            stmt.setString(2, UserName);
            stmt.executeUpdate();
        }
    }
}
