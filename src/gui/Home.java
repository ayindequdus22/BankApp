package gui;


 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 import java.sql.*;

public class Home extends JFrame implements ActionListener{

    public Home() {
        setVisible(true);
        setLocation(500,250);
        setResizable(false);
        setDefaultCloseOperation(2);
        setSize(500, 189);
        setTitle("GTB Bank");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        JButton viewBalanceBtn = new JButton("View Balance");
        JButton depositBtn = new JButton("Deposit");
        JButton transferBtn = new JButton("Transfer");
        JButton withdrawalBtn = new JButton("Withdrawal");
        JButton transactionBtn = new JButton("Transactions");
        JButton profileBtn = new JButton("Profile");

        Dimension buttonSize = new Dimension(150, 50);
        viewBalanceBtn.setPreferredSize(buttonSize);
        depositBtn.setPreferredSize(buttonSize);
        transferBtn.setPreferredSize(buttonSize);
        withdrawalBtn.setPreferredSize(buttonSize);
        transactionBtn.setPreferredSize(buttonSize);
        profileBtn.setPreferredSize(buttonSize);

        viewBalanceBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        transferBtn.addActionListener(this);
        withdrawalBtn.addActionListener(this);
        transactionBtn.addActionListener(this);
        profileBtn.addActionListener(this);

        buttonPanel.add(viewBalanceBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(withdrawalBtn);
        buttonPanel.add(transactionBtn);
        buttonPanel.add(profileBtn);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        add(mainPanel);

        // Set initial current frame as the main frame
        // currentFrame = this;

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Close other pages when main frame gains focus
                // if (currentFrame != null && currentFrame != Home.this) {
                //     currentFrame.dispose();
                //     currentFrame.setVisible(false);
                // }
            }
        });

    }

    private String getUserNameFromDatabase() {
        String UserName = null;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Establish connection to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");

            // Create and execute SQL query to retrieve username
            String query = "SELECT UserName FROM users WHERE id = ?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, 1);
            rs = stmt.executeQuery();

            // If the result set has a record, get the username value
            if (rs.next()) {
                UserName = rs.getString("UserName");
            }
        } catch (SQLException e) {
            e.printStackTrace();
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

        return UserName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        if (source.getText().equals("View Balance")) {
            setVisible(false);
            String UserName = getUserNameFromDatabase();
            dispose();
            ViewBalance.username = UserName;
            new  ViewBalance();
        } else if (source.getText().equals("Deposit")) {
            setVisible(false);
            String UserName = getUserNameFromDatabase();
            new Deposit(UserName);
        } else if (source.getText().equals("Transfer")) {
            setVisible(false);
            String UserName = getUserNameFromDatabase();
            new Transfer(UserName);
        } else if (source.getText().equals("Withdrawal")) {
            setVisible(false);
            String UserName = getUserNameFromDatabase();
            new Withdrawal(UserName);
        } else if (source.getText().equals("Transactions")) {
            setVisible(false);
            new Transactions();
        } else if (source.getText().equals("Profile")) {
            String UserName = getUserNameFromDatabase();
            setVisible(false);
            new Profile(UserName);
        }
    }

}
