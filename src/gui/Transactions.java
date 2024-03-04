package gui;

import javax.swing.*;
import java.awt.*;

public class Transactions extends JFrame {
    public Transactions() {
        setVisible(true);
        setTitle("Transactions");
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
        new Home();
    }
}
