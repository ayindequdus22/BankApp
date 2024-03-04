package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Withdrawal extends  JFrame implements ActionListener{
    private JLabel amountLabel;
    private JTextField amountField;
    private JButton withdrawButton;

    public Withdrawal() {
        
        amountLabel = new JLabel("Enter Withdrawal Amount:");
        amountField = new JTextField(10);
        withdrawButton = new JButton("Withdraw");
        
        withdrawButton.addActionListener(this);
        setVisible(true);
        setTitle("Withdrawal");
        setSize(400, 300);
        setLocation(500,250);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 JPanel mainPanel = new JPanel();
       mainPanel.setLayout(new GridLayout(2, 1));
       mainPanel.add(amountLabel);
       mainPanel.add(amountField);
       
 JPanel btnPanel = new JPanel();
       btnPanel.add(withdrawButton);



       
        add(mainPanel,BorderLayout.NORTH);
        add(btnPanel,BorderLayout.SOUTH);
    }
    @Override
    public void dispose() {
        super.dispose(); // Call the superclass method
        new Home();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == withdrawButton) {
            String amountString = amountField.getText();
            try {
                double amount = Double.parseDouble(amountString);
                if (amount > 5000) {
                    JOptionPane.showMessageDialog(this, "Withdrawal not possible,either deposit or reduce the amount to be withdrawn");
                }
                else{
                JOptionPane.showMessageDialog(this, "Withdrawal successful!");
                dispose();
            } 
        }catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount.");
            }
        }
      }
}
