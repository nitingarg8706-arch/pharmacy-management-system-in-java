import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// iText imports
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BillingWindow extends JFrame {

    DefaultListModel<Item> cart = new DefaultListModel<>();
    JList<Item> cartList = new JList<>(cart);
    JLabel totalLabel = new JLabel("₹0.0");

    JTextField customerNameField = new JTextField();
    JTextField fatherNameField = new JTextField();
    JTextField customerPhoneField = new JTextField();
    JTextField customerAgeField = new JTextField();
    JTextField customerAddressField = new JTextField();

    private static final String GST_NUMBER = "29ABCDE1234F1Z5";
    private double finalAmount = 0.0;

    public BillingWindow() {
        setTitle("Billing Window");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            setContentPane(new JLabel(new ImageIcon("images/pharmacy.jpg")));
        } catch (Exception e) {
            setContentPane(new JPanel());
        }

        setLayout(new BorderLayout());

        // ===== Customer Panel =====
        JPanel customerPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        customerPanel.setOpaque(false);

        customerPanel.add(new JLabel("Patient Name:"));
        customerPanel.add(customerNameField);

        customerPanel.add(new JLabel("Father's Name:"));
        customerPanel.add(fatherNameField);

        customerPanel.add(new JLabel("Phone:"));
        customerPanel.add(customerPhoneField);

        customerPanel.add(new JLabel("Age:"));
        customerPanel.add(customerAgeField);

        customerPanel.add(new JLabel("Address:"));
        customerPanel.add(customerAddressField);

        add(customerPanel, BorderLayout.NORTH);

        // ===== Cart =====
        add(new JScrollPane(cartList), BorderLayout.CENTER);

        // ===== Bottom Panel =====
        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.setOpaque(false);

        JButton addBtn = new JButton("Add Medicine");
        JButton removeBtn = new JButton("Remove Medicine");
        JButton generateBtn = new JButton("Proceed To Payment");

        bottomPanel.add(totalLabel);
        bottomPanel.add(addBtn);
        bottomPanel.add(removeBtn);
        bottomPanel.add(generateBtn);

        add(bottomPanel, BorderLayout.SOUTH);

        // ===== Add Medicine =====
        addBtn.addActionListener(e -> {
            Item selected = (Item) JOptionPane.showInputDialog(
                    this,
                    "Select Medicine",
                    "Add Medicine",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    PharmacyDatabase.inventory.toArray(),
                    null
            );

            if (selected != null) {
                String qtyStr = JOptionPane.showInputDialog(this, "Enter Quantity:");
                if (qtyStr == null) return;

                try {
                    int qty = Integer.parseInt(qtyStr);

                    if (qty <= 0) {
                        JOptionPane.showMessageDialog(this, "Quantity must be greater than 0!");
                        return;
                    }

                    if (qty > selected.quantity) {
                        JOptionPane.showMessageDialog(this, "Not enough stock!");
                        return;
                    }

                    selected.quantity -= qty;

                    Item cartItem = new Item(
                            selected.name,
                            qty,
                            selected.price,
                            selected.manufactureDate,
                            selected.expiryDate
                    );

                    cart.addElement(cartItem);
                    updateTotal();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid quantity!");
                }
            }
        });

        // ===== Remove Medicine =====
        removeBtn.addActionListener(e -> {
            Item selected = cartList.getSelectedValue();

            if (selected != null) {
                for (Item i : PharmacyDatabase.inventory) {
                    if (i.name.equals(selected.name) && i.price == selected.price) {
                        i.quantity += selected.quantity;
                        break;
                    }
                }
                cart.removeElement(selected);
                updateTotal();
            } else {
                JOptionPane.showMessageDialog(this, "Select a medicine to remove!");
            }
        });

        // ===== Proceed to Payment =====
        generateBtn.addActionListener(e -> {

            if (cart.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Cart is empty!");
                return;
            }

            // ✅ VALIDATE CUSTOMER DETAILS BEFORE PAYMENT
            if (!validateCustomerDetails()) {
                return;
            }

            double subtotal = calculateTotal();
            double gst = subtotal * 0.18;
            double totalPayable = subtotal + gst;

            PaymentWindow paymentWindow = new PaymentWindow(totalPayable, (paidAmount) -> {
                this.finalAmount = paidAmount;
                generateBillPDF(); // Now runs AFTER payment
            });

            paymentWindow.setVisible(true);
        });

        setVisible(true);
    }

    private void updateTotal() {
        totalLabel.setText("₹" + calculateTotal());
    }

    private double calculateTotal() {
        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            Item it = cart.get(i);
            total += it.price * it.quantity;
        }
        return total;
    }

    // ========= VALIDATION METHODS =========

    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z ]+");
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{10}");
    }

    private boolean isValidAge(String age) {
        try {
            int a = Integer.parseInt(age);
            return a > 0 && a <= 120;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isValidAddress(String address) {
        return address != null && address.trim().length() >= 5;
    }

    private boolean validateCustomerDetails() {

        String customerName = customerNameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String phone = customerPhoneField.getText().trim();
        String age = customerAgeField.getText().trim();
        String address = customerAddressField.getText().trim();

        if (!isValidName(customerName)) {
            JOptionPane.showMessageDialog(this, "Enter valid Patient Name");
            return false;
        }

        if (!isValidName(fatherName)) {
            JOptionPane.showMessageDialog(this, "Enter valid Father Name");
            return false;
        }

        if (!isValidPhone(phone)) {
            JOptionPane.showMessageDialog(this, "Enter valid 10-digit Phone Number");
            return false;
        }

        if (!isValidAge(age)) {
            JOptionPane.showMessageDialog(this, "Enter valid Age (1 - 120)");
            return false;
        }

        if (!isValidAddress(address)) {
            JOptionPane.showMessageDialog(this, "Enter valid Address (Min 5 characters)");
            return false;
        }

        return true;
    }

    // ========= PDF GENERATION =========

    private void generateBillPDF() {

        String customerName = customerNameField.getText().trim();
        String fatherName = fatherNameField.getText().trim();
        String phone = customerPhoneField.getText().trim();
        String age = customerAgeField.getText().trim();
        String address = customerAddressField.getText().trim();

        try {
            File billsFolder = new File("bills");
            if (!billsFolder.exists()) billsFolder.mkdir();

            Document document = new Document();
            String fileName = "bills/Bill_" + System.currentTimeMillis() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();

            Font normalFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);

            Paragraph header = new Paragraph("=== Pharmacy Invoice ===", titleFont);
            header.setAlignment(Element.ALIGN_CENTER);
            document.add(header);
            document.add(new Paragraph("\n"));

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            String dateTime = LocalDateTime.now().format(dtf);

            document.add(new Paragraph("Date & Time: " + dateTime, boldFont));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Patient: " + customerName, normalFont));
            document.add(new Paragraph("Father's Name: " + fatherName, normalFont));
            document.add(new Paragraph("Phone: " + phone, normalFont));
            document.add(new Paragraph("Age: " + age, normalFont));
            document.add(new Paragraph("Address: " + address, normalFont));
            document.add(new Paragraph("GST No: " + GST_NUMBER, normalFont));
            document.add(new Paragraph("\n"));

            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);

            table.addCell(new PdfPCell(new Phrase("Medicine", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Qty", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Price", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Mfg Date", boldFont)));
            table.addCell(new PdfPCell(new Phrase("Expiry Date", boldFont)));

            double subtotal = 0;

            for (int i = 0; i < cart.size(); i++) {
                Item it = cart.get(i);

                table.addCell(new Phrase(it.name, normalFont));
                table.addCell(new Phrase(String.valueOf(it.quantity), normalFont));
                table.addCell(new Phrase("₹" + it.price, normalFont));
                table.addCell(new Phrase(it.manufactureDate, normalFont));
                table.addCell(new Phrase(it.expiryDate, normalFont));

                subtotal += it.price * it.quantity;
            }

            document.add(table);

            double gstAmount = subtotal * 0.18;
            double totalBill = subtotal + gstAmount;
            double exchangeAmount = finalAmount - totalBill;

            document.add(new Paragraph("\nSubtotal: ₹" + subtotal, boldFont));
            document.add(new Paragraph("GST (18%): ₹" + gstAmount, boldFont));
            document.add(new Paragraph("Total Bill: ₹" + totalBill, boldFont));
            document.add(new Paragraph("Customer Paid: ₹" + finalAmount, boldFont));

            if (exchangeAmount >= 0) {
                document.add(new Paragraph("Return: ₹" + exchangeAmount, boldFont));
            } else {
                document.add(new Paragraph("Pending Due: ₹" + Math.abs(exchangeAmount), boldFont));
            }

            document.add(new Paragraph("\nThank you for your purchase!", boldFont));

            document.close();

            JOptionPane.showMessageDialog(this, "Bill Generated Successfully\nSaved in: " + fileName);

            cart.clear();
            updateTotal();
            clearCustomerFields();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, " Error: " + e.getMessage());
        }
    }

    private void clearCustomerFields() {
        customerNameField.setText("");
        fatherNameField.setText("");
        customerPhoneField.setText("");
        customerAgeField.setText("");
        customerAddressField.setText("");
    }
}
