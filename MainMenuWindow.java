// File: MainMenuWindow.java
import javax.swing.*;
import java.awt.*;

public class MainMenuWindow extends JFrame {
    public MainMenuWindow(String role) {
        setTitle("Dashboard - " + role);
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Background image panel
        BackgroundPanel panel = new BackgroundPanel("images/inventory.jpg");
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0); // space between buttons
        gbc.gridx = 0;

        // Button style
        Font buttonFont = new Font("Arial", Font.BOLD, 18);

        JButton inventoryBtn = createStyledButton("View Inventory", buttonFont);
        JButton addBtn = createStyledButton("Add Item", buttonFont);
        JButton billBtn = createStyledButton("Create Bill", buttonFont);
        JButton exitBtn = createStyledButton("Exit", buttonFont);

        // Add actions
        inventoryBtn.addActionListener(e -> new InventoryWindow());
        addBtn.addActionListener(e -> new AddItemWindow());
        billBtn.addActionListener(e -> new BillingWindow());
        exitBtn.addActionListener(e -> System.exit(0));

        // Add buttons to layout as rows
        gbc.gridy = 0; panel.add(inventoryBtn, gbc);
        gbc.gridy = 1; panel.add(addBtn, gbc);
        gbc.gridy = 2; panel.add(billBtn, gbc);
        gbc.gridy = 3; panel.add(exitBtn, gbc);

        // Disable addBtn if role is Pharmacist
        if (role.equalsIgnoreCase("Pharmacist")) addBtn.setEnabled(false);

        add(panel);
        setVisible(true);
    }

    // Helper to create styled transparent buttons
    private JButton createStyledButton(String text, Font font) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setPreferredSize(new Dimension(250, 50));
        btn.setForeground(Color.BLACK);
        btn.setFocusPainted(false);
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return btn;
    }
}
