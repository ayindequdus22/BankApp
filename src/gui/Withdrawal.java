package gui;

import javax.swing.*;
import java.awt.*;

public class Withdrawal extends  JFrame{
    public Withdrawal() {
        setVisible(true);
        setTitle("Profile");
        setSize(400, 300);
        setLocation(500,250);
        setVisible(true);
        setResizable(false);
        JPanel mainPanel = new JPanel(new BorderLayout());
        add(mainPanel);
    }
}


// public class BankTransferGUI extends JFrame {
  

//     public BankTransferGUI() {
      
//     public static void main(String[] args) {
//         // Run the GUI on the event dispatch thread
//         SwingUtilities.invokeLater(new Runnable() {
//             public void run() {
//                 new BankTransferGUI();
//             }
//         });
//     }
// }
