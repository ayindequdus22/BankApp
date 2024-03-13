public class file {
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import java.net.URL;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    
    public class SignInPage extends JFrame implements ActionListener {
    
        private JTextField usernameField;
        private JPasswordField passwordField;
        private JButton loginButton, signupButton;
        private JLabel usernameLabel, passwordLabel;
        private JPanel panel, imgPanel, buttonPanel;
    
        Connection conn;
        Statement st;
    
        public SignInPage() {
    
            setTitle("Login");
            setSize(400, 700);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
    
            panel = new JPanel(new GridLayout(2, 2));
            imgPanel = new JPanel(new BorderLayout());
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    
            usernameLabel = new JLabel("Username:");
            passwordLabel = new JLabel("Password:");
            usernameField = new JTextField();
            passwordField = new JPasswordField();
            loginButton = new JButton("Login");
            signupButton = new JButton("Signup");
    
            loginButton.addActionListener(this);
            signupButton.addActionListener(e -> {
                new SignUpPage().setVisible(true);
                dispose();
            });
    
            panel.add(usernameLabel);
            panel.add(usernameField);
            panel.add(passwordLabel);
            panel.add(passwordField);
    
            buttonPanel.add(loginButton);
            buttonPanel.add(signupButton);
    
            try {
                URL imageUrl = getClass().getResource("/images/logo.png");
                if (imageUrl != null) {
                    ImageIcon image = new ImageIcon(imageUrl);
                    JLabel imageLabel = new JLabel(image);
                    imgPanel.add(imageLabel, BorderLayout.CENTER);
                } else {
                    System.err.println("Image not found.");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    
            add(imgPanel, BorderLayout.NORTH);
            add(panel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.SOUTH);
    
            setVisible(true);
    
            loadSql();
        }
    
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == loginButton) {
                String UserName = usernameField.getText();
                String Password = new String(passwordField.getPassword());
    
                if (!UserName.isEmpty() && !Password.isEmpty()) {
                    try {
                        String query = "SELECT * FROM users WHERE UserName='" + UserName + "' AND Password='" + Password + "'";
                        ResultSet rs = st.executeQuery(query);
                        if (rs.next()) {
                            dispose();
                            setVisible(false);
                            new Home();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password");
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "All fields are required");
                }
            }
        }
    
        public void loadSql() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankappdb", "root", "");
                st = conn.createStatement();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    
        public static void main(String[] args) {
            new SignInPage();
        }
    }
    
}
