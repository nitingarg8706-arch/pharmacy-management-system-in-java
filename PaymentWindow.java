import javax.swing.*;
import java.awt.*;
import java.util.function.DoubleConsumer;  //  add this

public class PaymentWindow extends JFrame {
    private final double finalAmount;
    private final DoubleConsumer onConfirm;   //  callback that receives paidAmount

    private JTextField paidAmountField = new JTextField();
    private JLabel balanceLabel = new JLabel("₹0.0");
    private JLabel totalLabel = new JLabel("₹0.0");

    public PaymentWindow(double finalAmount, DoubleConsumer onConfirm) {
        this.finalAmount = finalAmount;
        this.onConfirm = onConfirm;

        setTitle("Payment");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Final Total: "));
        totalLabel.setText("₹" + finalAmount);
        panel.add(totalLabel);

        panel.add(new JLabel("Amount Paid: "));
        panel.add(paidAmountField);

        panel.add(new JLabel("Balance: "));
        panel.add(balanceLabel);

        JButton confirmBtn = new JButton("Confirm Payment");
        JButton cancelBtn = new JButton("Cancel");

        confirmBtn.addActionListener(e -> confirmPayment());
        cancelBtn.addActionListener(e -> dispose());

        panel.add(confirmBtn);
        panel.add(cancelBtn);

        add(panel, BorderLayout.CENTER);
    }

    private void confirmPayment() {
        try {
            double paid = Double.parseDouble(paidAmountField.getText());
            if (paid < finalAmount) {
                JOptionPane.showMessageDialog(this, "Paid amount is less than total bill!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            double balance = paid - finalAmount;
            balanceLabel.setText("₹" + balance);

            JOptionPane.showMessageDialog(this,
                    "Payment Successful!\nBalance/Change: ₹" + balance,   //payment can given in cash only
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            if (onConfirm != null) onConfirm.accept(paid);   // send paidAmount back
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
