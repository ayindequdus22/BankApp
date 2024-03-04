package gui;

import javax.swing.*;
import java.awt.*;
public class ViewBalance extends JFrame {
    public ViewBalance() {
        setVisible(true);
        setTitle("View Balance");
        setSize(400, 300);
        setLocation(500,250);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
    }
    @Override
    public void dispose() {
        super.dispose(); // Call the superclass method
        new Home().setVisible(true); // Open the home page when the transfer page is closed
    }
}
