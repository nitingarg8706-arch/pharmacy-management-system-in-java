import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {
    JTextField userField;
    JPasswordField passField;

    public LoginWindow() {
        setTitle("Login - Pharmacy System");
        setSize(612, 421);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Background image panel with light overlay
        BackgroundPanel panel = new BackgroundPanel("images/login.jpg") {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(0, 0, 0, 100)); // light transparent overlay
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new GridBagLayout());

        // Login panel
        JPanel loginPanel = new JPanel(new GridLayout(4, 1, 8, 8));
        loginPanel.setBackground(new Color(255, 255, 255, 220));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel title = new JLabel("Pharmacy Login", JLabel.CENTER);
        title.setFont(new Font("Times new roman", Font.BOLD, 18));
        title.setForeground(new Color(0, 102, 204));

        userField = new JTextField();
        userField.setFont(new Font("Times new roman", Font.PLAIN, 14));

        passField = new JPasswordField();
        passField.setFont(new Font("Times new roman", Font.PLAIN, 14));

        JButton loginBtn = new JButton("Login");
        loginBtn.setBackground(new Color(0, 102, 204));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Times new roman", Font.BOLD, 14));
        loginBtn.setFocusPainted(false);
        loginBtn.addActionListener(e -> authenticate());

        loginPanel.add(title);
        loginPanel.add(userField);
        loginPanel.add(passField);
        loginPanel.add(loginBtn);

        panel.add(loginPanel);
        add(panel);
        setVisible(true);
    }

    void authenticate() {
        String u = userField.getText();
        String p = new String(passField.getPassword());

        if ((u.equals("nitin") && p.equals("0000")) || (u.equals("garg") && p.equals("1234"))) {
            JOptionPane.showMessageDialog(this, "Login successful as " + (u.equals("nitin") ? "Admin" : "Pharmacist"));
            dispose();
            new MainMenuWindow(u.equals("nitin") ? "Admin" : "Pharmacist");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials!");
        }
    }
}
