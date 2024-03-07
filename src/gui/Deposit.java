package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Deposit extends JFrame {
    JLabel accountbalanceLabel = new JLabel("Account balance:");
    JLabel accountBalance = new JLabel("$30,000"); // Assuming this is the initial balance
    JLabel amountTobeDepositedLabel = new JLabel("Deposit:");
    JTextField amountTobeDeposited = new JTextField(3);

    Connection conn;

    public Deposit() {
        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");

            // Set auto-commit to false to manage transactions manually
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        setVisible(true);
        setTitle("Deposit");
        setSize(400, 300);
        setLocation(500, 250);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
                depositMoney();
            }
        });
        buttonPanel.add(depositButton);
    }

    private void depositMoney() {
        if (amountTobeDeposited.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please input an amount to be deposited", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                double amount = Double.parseDouble(amountTobeDeposited.getText());
                System.out.println(amount);


                // Get the current balance from the label
                double currentBalance = Double.parseDouble(accountBalance.getText().substring(1));
                System.out.println(amount);

                // Calculate the new balance after deposit
                double newBalance = currentBalance + amount;

                // Update the balance label
                accountBalance.setText("$" + newBalance);

                // Update the balance in the database
                updateBalanceInDatabase(newBalance);

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

    private void updateBalanceInDatabase(double newBalance) throws SQLException {
        // Create a prepared statement to update the balance in the database
        String query = "UPDATE users SET Balance = ? WHERE Username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setDouble(1, newBalance);
        stmt.executeUpdate();
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
        new Home();
    }
}

