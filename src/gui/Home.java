package gui;

 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Home extends JFrame implements ActionListener{
   

    private JFrame currentFrame;

    public Home() {
        setVisible(true);
        setLocation(500,250);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 189);
        setTitle("GTB Bank");

        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        JButton viewBalanceBtn = new JButton("View Balance");
        JButton depositBtn = new JButton("Deposit");
        JButton transferBtn = new JButton("Transfer");
        JButton withdrawalBtn = new JButton("Withdrawal");
        JButton transactionBtn = new JButton("Transactions");
        JButton profileBtn = new JButton("Profile");

        Dimension buttonSize = new Dimension(150, 50);
        viewBalanceBtn.setPreferredSize(buttonSize);
        depositBtn.setPreferredSize(buttonSize);
        transferBtn.setPreferredSize(buttonSize);
        withdrawalBtn.setPreferredSize(buttonSize);
        transactionBtn.setPreferredSize(buttonSize);
        profileBtn.setPreferredSize(buttonSize);

        viewBalanceBtn.addActionListener(this);
        depositBtn.addActionListener(this);
        transferBtn.addActionListener(this);
        withdrawalBtn.addActionListener(this);
        transactionBtn.addActionListener(this);
        profileBtn.addActionListener(this);

        buttonPanel.add(viewBalanceBtn);
        buttonPanel.add(depositBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(withdrawalBtn);
        buttonPanel.add(transactionBtn);
        buttonPanel.add(profileBtn);
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        add(mainPanel);

        // Set initial current frame as the main frame
        currentFrame = this;

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                // Close other pages when main frame gains focus
                if (currentFrame != null && currentFrame != Home.this) {
                    currentFrame.dispose();
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton)e.getSource();
        if (source.getText().equals("View Balance")) {
            currentFrame = new ViewBalance();
        } else if (source.getText().equals("Deposit")) {
            currentFrame = new Deposit();
        } else if (source.getText().equals("Transfer")) {
            currentFrame = new Transfer();
        } else if (source.getText().equals("Withdrawal")) {
            currentFrame = new Withdrawal();
        } else if (source.getText().equals("Transactions")) {
            currentFrame = new Transactions();
        } else if (source.getText().equals("Profile")) {
            currentFrame = new Profile();
        }
    }

//     // public static void main(String[] args) {
//     //     new Main();
//     // }
// }

}
