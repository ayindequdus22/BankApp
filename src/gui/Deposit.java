package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Deposit extends JFrame {
    JLabel accountbalanceLabel = new JLabel("Account balance:");
        JLabel accountBalance = new JLabel("$3000");
        JLabel amountTobeDepositedLabel = new JLabel("Deposit:");
        JTextField amountTobeDeposited = new JTextField(3);
    public Deposit() {
        setVisible(true);
        setTitle("Deposit");
        setSize(400, 300);
        setLocation(500, 250);
        setVisible(true);
        setResizable(false);
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
               
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
       
  
}
