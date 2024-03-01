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
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
    }
}
