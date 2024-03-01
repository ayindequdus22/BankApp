package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transfer extends JFrame {
    private JTextField fromAccountField;
    private JTextField toAccountField;
    private JTextField amountField;
    private JButton transferButton;

    public Transfer() {
        setVisible(true);
        setTitle("Transfer");
        setSize(400, 200);
        setLocation(500, 250);
        setVisible(true);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        JPanel buttonPanel = new JPanel();
        add(mainPanel);
        add(buttonPanel, BorderLayout.SOUTH);

        JLabel fromLabel = new JLabel("From Account:");
        fromAccountField = new JTextField(10);
        JLabel toLabel = new JLabel("To Account:");
        toAccountField = new JTextField(10);
        JLabel amountLabel = new JLabel("Amount:");
        amountField = new JTextField(10);
        transferButton = new JButton("Transfer");

        mainPanel.add(fromLabel);
        mainPanel.add(fromAccountField);
        mainPanel.add(toLabel);
        mainPanel.add(toAccountField);
        mainPanel.add(amountLabel);
        mainPanel.add(amountField);
        transferButton.setPreferredSize(new Dimension(400, 30));

        buttonPanel.add(transferButton);

        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                transferMoney();
            }
        });
    }

    private void transferMoney() {
        String fromAccount = fromAccountField.getText();
        String toAccount = toAccountField.getText();
        String amountText = amountField.getText();
    
        if (fromAccount.isEmpty() || toAccount.isEmpty() || amountText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all the fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                double amount = Double.parseDouble(amountText);
                System.out.println("Transfer from " + fromAccount + " to " + toAccount + " amount: $" + amount);
                fromAccountField.setText("");
                toAccountField.setText("");
                amountField.setText(""); 
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
            
    }
