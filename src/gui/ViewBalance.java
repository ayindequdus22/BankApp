package gui;
import gui.Home;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ViewBalance extends JFrame {
public  static String username = null;
    private JLabel balanceLabel;

    public ViewBalance() {
        setTitle("View Balance");
        setSize(400, 300);
        setLocation(500, 250);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        setDefaultCloseOperation(2);

        JPanel mainPanel = new JPanel(new BorderLayout());
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
        super.dispose();
        new Home();
    }
}
