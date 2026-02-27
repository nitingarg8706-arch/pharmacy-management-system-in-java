import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InventoryWindow extends JFrame {
    private DefaultListModel<Item> inventoryModel = new DefaultListModel<>();
    private JList<Item> inventoryList = new JList<>(inventoryModel);
    private JTextField searchField = new JTextField();
    private JButton addBtn = new JButton("Add");
    private JButton editBtn = new JButton("Edit");
    private JButton deleteBtn = new JButton("Delete");

    public InventoryWindow() {
        setTitle("Inventory Management");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Background image
        ImageIcon bgImg = new ImageIcon("images/inventory.jpg"); // <-- put your image path here
        JLabel bgLabel = new JLabel(bgImg);
        bgLabel.setLayout(new BorderLayout());
        setContentPane(bgLabel);

        loadInventoryItems();

        // Top panel for search
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(new JLabel("Search: "), BorderLayout.WEST);
        topPanel.add(searchField, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Center panel for item list
        JScrollPane scrollPane = new JScrollPane(inventoryList);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel for buttons
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.add(addBtn);
        bottomPanel.add(editBtn);
        bottomPanel.add(deleteBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        // Event: search
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { filterList(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { filterList(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { filterList(); }
        });

        addBtn.addActionListener(e -> addNewItem());
        editBtn.addActionListener(e -> editSelectedItem());
        deleteBtn.addActionListener(e -> deleteSelectedItem());

        setVisible(true);
    }

    private void loadInventoryItems() {
        inventoryModel.clear();
        for (Item i : PharmacyDatabase.inventory) {
            inventoryModel.addElement(i);
        }
    }

    private void filterList() {
        String query = searchField.getText().toLowerCase();
        inventoryModel.clear();
        for (Item i : PharmacyDatabase.inventory) {
            if (i.name.toLowerCase().contains(query)) {
                inventoryModel.addElement(i);
            }
        }
    }

    private void addNewItem() {
        String name = JOptionPane.showInputDialog(this, "Enter Medicine Name:");
        if (name == null || name.trim().isEmpty()) return;
        try {
            int qty = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter Quantity:"));
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter Price:"));
            String mfg = JOptionPane.showInputDialog(this, "Enter Manufacture Date (dd-mm-yyyy):");
            String exp = JOptionPane.showInputDialog(this, "Enter Expiry Date (dd-mm-yyyy):");
            Item newItem = new Item(name, qty, price, mfg, exp);
            PharmacyDatabase.inventory.add(newItem);
            loadInventoryItems();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void editSelectedItem() {
        Item selected = inventoryList.getSelectedValue();
        if (selected == null) return;
        try {
            String name = JOptionPane.showInputDialog(this, "Edit Name:", selected.name);
            int qty = Integer.parseInt(JOptionPane.showInputDialog(this, "Edit Quantity:", selected.quantity));
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Edit Price:", selected.price));
            String mfg = JOptionPane.showInputDialog(this, "Edit Manufacture Date:", selected.manufactureDate);
            String exp = JOptionPane.showInputDialog(this, "Edit Expiry Date:", selected.expiryDate);
            selected.name = name;
            selected.quantity = qty;
            selected.price = price;
            selected.manufactureDate = mfg;
            selected.expiryDate = exp;
            loadInventoryItems();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input.");
        }
    }

    private void deleteSelectedItem() {
        Item selected = inventoryList.getSelectedValue();
        if (selected != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure?");
            if (confirm == JOptionPane.YES_OPTION) {
                PharmacyDatabase.inventory.remove(selected);
                loadInventoryItems();
            }
        }
    }
}
