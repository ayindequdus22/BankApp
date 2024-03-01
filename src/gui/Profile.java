package gui;
import javax.swing.*;
import java.awt.*;

public class Profile extends JFrame {
    public Profile() {
        setTitle("View Balance");
        setSize(400, 300);
        setLocation(500,250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(3, 2));


        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(new JLabel("Adeola Yisa"));
        mainPanel.add(new JLabel("Account Number:"));
        mainPanel.add(new JLabel("1234567890"));
        mainPanel.add(new JLabel("Balance:"));
        mainPanel.add(new JLabel("$1000.00"));

        add(mainPanel);

        setVisible(true);
        setResizable(false);
    }

}
