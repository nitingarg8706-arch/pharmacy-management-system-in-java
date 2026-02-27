// File: AddItemWindow.java
import javax.swing.*;
import java.awt.*;

public class AddItemWindow extends JFrame {
    public AddItemWindow() {
        setTitle("Add Item");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Input fields
        JTextField name = new JTextField();
        JTextField qty = new JTextField();
        JTextField price = new JTextField();
        JTextField mfgDate = new JTextField();
        JTextField expiryDate = new JTextField();
        JLabel status = new JLabel();

        // Buttons
        JButton addBtn = new JButton("Add");

        // Labels + Fields
        add(new JLabel("Name:")); add(name);
        add(new JLabel("Qty:")); add(qty);
        add(new JLabel("Price:")); add(price);
        add(new JLabel("MFG Date (dd-mm-yyyy):")); add(mfgDate);
        add(new JLabel("Expiry Date (dd-mm-yyyy):")); add(expiryDate);
        add(addBtn); add(status);

        // Action
        addBtn.addActionListener(e -> {
            try {
                String itemName = name.getText().trim();
                int itemQty = Integer.parseInt(qty.getText().trim());
                double itemPrice = Double.parseDouble(price.getText().trim());
                String mfg = mfgDate.getText().trim();
                String exp = expiryDate.getText().trim();

                if (itemName.isEmpty() || mfg.isEmpty() || exp.isEmpty()) {
                    status.setText("Please fill all fields!");
                    return;
                }

                // Add to inventory
                PharmacyDatabase.inventory.add(
                    new Item(itemName, itemQty, itemPrice, mfg, exp)
                );
                status.setText("Item Added!");
                
                // clear fields
                name.setText("");
                qty.setText("");
                price.setText("");
                mfgDate.setText("");
                expiryDate.setText("");
            } catch (Exception ex) {
                status.setText("Error: Invalid Input!");
            }
        });

        setVisible(true);
    }
}
