package gui;

import javax.swing.*;
import java.awt.*;

public class Transactions extends JFrame {
    private JTextArea transactionArea;

    public Transactions() {
        setVisible(true);
        setTitle("Transactions");
        setSize(400, 300);
        setLocation(500, 250);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        transactionArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(transactionArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    public void appendTransaction(String transaction) {
        transactionArea.append(transaction + "\n");
    }

    @Override
    public void dispose() {
        super.dispose(); // Call the superclass method
        new Home();
    }

    public static void main(String[] args) {
        // Example usage
        Transactions transactionsWindow = new Transactions();
        transactionsWindow.appendTransaction("Transfer from User1 to User2: $100");
        transactionsWindow.appendTransaction("Deposit to User1: $50");
    }
}
