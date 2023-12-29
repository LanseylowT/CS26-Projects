package moneymanager.screens.overview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionDialog extends JDialog {
    private JTextField amountField;
    private boolean confirmed;

    public TransactionDialog(Component parent, String transactionType) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), true); // true for modal
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setPreferredSize(new Dimension(250, 150));
        setUndecorated(true);
        setLayout(new GridLayout(3, 1));

        JLabel label = new JLabel("Enter " + transactionType + " Amount:");
        amountField = new JTextField(10);

        JPanel buttonPanel = new JPanel();
        JButton confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        add(label);
        add(amountField);
        add(buttonPanel);

        pack();
        setLocationRelativeTo(parent);
    }

    public double getAmount() {
        String amountText = amountField.getText();
        return Double.parseDouble(amountText);
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
