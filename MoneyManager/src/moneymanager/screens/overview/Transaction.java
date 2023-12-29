package moneymanager.screens.overview;

import moneymanager.constants.TransactionPanelActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Transaction extends JDialog {

    public Transaction(Component parent, String transactionType, TransactionPanelActionListener actionListener) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), transactionType + " Amount", true); // true for modal
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        TransactionWindow transactionPanel = new TransactionWindow(transactionType, actionListener);

        add(transactionPanel);
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }
}
