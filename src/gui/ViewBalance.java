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
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
    }
}
