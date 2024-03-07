package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage extends JFrame {
    public LandingPage() {
        setTitle("Landing Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Create a JPanel to hold components
        JPanel panel = new JPanel() {
            // Override the paintComponent method to draw the background image
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Load the background image
                ImageIcon backgroundImage = new ImageIcon("src/images/back.jpeg");
                // Draw the background image
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setLayout(null); // Set layout manager to null
        panel.setPreferredSize(new Dimension(400, 700)); // Set preferred size
        panel.setOpaque(false); // Make the panel transparent

        // Add your components directly to the panel
        // Position them manually using setBounds method
        JLabel titleLabel = new JLabel("Welcome GTBANK");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(50, 50, 300, 50); // Example position and size
        panel.add(titleLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(255, 165, 0));
        loginButton.setFocusPainted(false);
        loginButton.setForeground(Color.white);
        loginButton.setBounds(150, 200, 100, 50); // Example position and size
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the login screen or perform login action
                new SignInPage();
                dispose(); // Close the current window
            }
        });
        panel.add(loginButton);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(150, 300, 100, 50); // Example position and size
        signUpButton.setBackground(new Color(255, 136, 0));
        signUpButton.setFocusPainted(false);
        signUpButton.setForeground(Color.white);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the sign-up screen or perform sign-up action
                new SignUpPage().setVisible(true);
                dispose(); // Close the current window
            }
        });
        panel.add(signUpButton);

        // Add the panel to the frame
        add(panel);

        // Adjust frame size to fit the panel
        pack();
        // Center the frame on the screen
        setLocationRelativeTo(null);
        // Set frame size to be larger
        setSize(400, 500);
        setVisible(true);
    }

}
