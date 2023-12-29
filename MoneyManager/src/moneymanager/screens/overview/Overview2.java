/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moneymanager.screens.overview;

import moneymanager.constants.Colors;
import moneymanager.model.MoneyModel;
import moneymanager.model.TransactionModel;
import moneymanager.screens.history.History;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * @author donla
 */
public class Overview2 extends javax.swing.JPanel {
//    private final Fonts customFont = new Fonts();
    private final MoneyModel moneyModel;
    JPanel transactionWindow = new JPanel();
    TransactionModel transactionModel;
    History historyScreen;

    /**
     * Creates new form Overview2
     */
    // Make a documentation based on the code below
    // This is the constructor for the Overview2 class which is a JPanel class that is used in the Main2 class as a card layout panel (mainBody) in the constructor of the Main2 class (Main2.java) in the src/moneymanager/main/Main2.java file (line 34) and in the src/moneymanager/screens/overview/Overview2.java file (line 34) as well.
    public Overview2(MoneyModel moneyModel, TransactionModel transactionModel, History historyScreen) {
        this.historyScreen = historyScreen;
        this.moneyModel = moneyModel;
        this.transactionModel = transactionModel;
        this.historyScreen.setTransactionModel(this.transactionModel);
        initComponents();
        modelInit();
        init();
    }

    // region Getters and Setters
    // endregion

    public void modelInit(){
        transactionModel = new TransactionModel();
    }

    public void init() {
        transactionWindow.setPreferredSize(new Dimension(250, 150));
        depositButton.setButtonColor(Colors.mantis);
        depositButton.setButtonColorHovered(Colors.gray);
        withdrawButton.setButtonColor(Colors.cyclamen);
        withdrawButton.setButtonColorHovered(Colors.gray);
        historyScreen.setCurrencySign(moneyModel.getCurrencySign());
        historyScreen.setCurrentBalance(formatBalance(moneyModel.getBalance()));

        customComboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) customComboBox1.getSelectedItem(); // "Pesos - ₱"
                String[] currencySign = selectedOption.split("-"); // ["Pesos", "$"]
                String newSymbol = currencySign[1]; // $
                historyScreen.setCurrencySign(newSymbol);

                switch (selectedOption) {
                    case "Pesos - ₱" -> moneyModel.setNewValue(1);
                    case "Dollar - $" -> moneyModel.setNewValue(2);
                    case "Euro - €" -> moneyModel.setNewValue(3);
                    case "Yen - ¥" -> moneyModel.setNewValue(4);
                }
                updateCurrency(moneyModel.getNewValue(), newSymbol);
                repaint();
            }
        });
    }

    private String formatBalance(double balance) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return decimalFormat.format(balance);
    }

    public void updateCurrency(int newValueSymbol, String newSymbol) {
        // Rates
        double pesRate = 55.86;
        double dolRate = 1 / 55.86;
        double euroRate = 0.92;
        double yenRate = 0.39;

        double remainingBalance = moneyModel.getBalance();
        switch (moneyModel.getValue()) {
            case 1:
                break;
            case 2:
                moneyModel.setBalance(remainingBalance = remainingBalance * pesRate);
                break;
            case 3:
                moneyModel.setBalance(remainingBalance = remainingBalance *  pesRate / euroRate);
                break;
            case 4:
                moneyModel.setBalance(remainingBalance = remainingBalance / yenRate);
                break;
        }
        switch (newValueSymbol) {
            case 1:
                break;
            case 2:
                moneyModel.setBalance(remainingBalance *= dolRate);
                break;
            case 3:
                moneyModel.setBalance(remainingBalance *= euroRate / pesRate);
                break;
            case 4:
                moneyModel.setBalance(remainingBalance *= yenRate);
                break;
        }

        moneyModel.setValue(moneyModel.getNewValue());
        moneyModel.setCurrencySign(newSymbol);
        currencySign.setText(newSymbol);
        updateBalanceLabel();
    }

    public void updateBalanceLabel() {
        double remainingBalance = moneyModel.getBalance();
        currencyBalance.setText(formatBalance(remainingBalance)); // 1,500
        historyScreen.setCurrentBalance(formatBalance(moneyModel.getBalance()));
        historyScreen.setCurrencySign(moneyModel.getCurrencySign());
    }

    public void updateTransaction(){
        if (transactionModel.getTransactionHistory() != null){
            historyScreen.showTransactionHistory();
        }
    }

    public void showTransactionDialog(Component parent, String transactionType) {
        double amount = 0;
        TransactionDialog dialog = new TransactionDialog(parent, transactionType);
        dialog.setVisible(true);

        // You can retrieve the entered amount from the dialog if needed
        try {
            amount = dialog.getAmount();
        } catch (Exception ignored) {
            System.out.println("invalid");
        }
        if (dialog.isConfirmed()) {
            // Process withdrawal or deposit based on the amount
            switch (transactionType) {
                case "Deposit":
                    deposit(amount);
                    transactionModel.addTransaction("Added: " + currencySign.getText() + " " + formatBalance(amount));
                    updateTransaction();
                    break;
                case "Withdraw":
                    withdraw(amount);
                    updateTransaction();
                    break;
                case "Update":
                    moneyModel.setBalance(amount);
                    transactionModel.addTransaction("Updated: " + currencySign.getText() + " " + formatBalance(amount));
                    updateTransaction();
                    updateBalanceLabel();
            }
        } else {
            // Handle cancellation
            System.out.println(transactionType + " cancelled");
        }
    }

    private void withdraw(double amount) {
        double remainingBalance = moneyModel.getBalance();
        try {
            if (amount > remainingBalance) {
                JOptionPane.showMessageDialog(null, "Insufficient balance!");
                transactionModel.addTransaction("Denied: " + currencySign.getText() + " " + formatBalance(amount));
            } else {
                moneyModel.setBalance(remainingBalance - amount); // 100000
                transactionModel.addTransaction("Spent: " + currencySign.getText() + " " + formatBalance(amount));
                updateBalanceLabel(); // 100,000
            }
        } catch (Exception ignored) {}
    }

    private void deposit(double amount) {
        double remainingBalance = moneyModel.getBalance(); //26.00
        try {
            moneyModel.setBalance(remainingBalance + amount);
            updateBalanceLabel();
        } catch (Exception ignored) {}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customRoundedPanel1 = new moneymanager.customs.CustomRoundedPanel();
        currencySign = new javax.swing.JLabel();
        currencyBalance = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        withdrawButton = new moneymanager.customs.CustomButton3();
        depositButton = new moneymanager.customs.CustomButton3();
        customComboBox1 = new moneymanager.customs.CustomComboBox();
        customButton31 = new moneymanager.customs.CustomButton3();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        customRoundedPanel1.setBackground(Colors.secondaryColor);

        currencySign.setFont(new java.awt.Font("Dialog", 0, 45)); // NOI18N
        currencySign.setForeground(new java.awt.Color(255, 255, 255));
        currencySign.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        currencySign.setText("₱");

        currencyBalance.setFont(new java.awt.Font("Dialog", 0, 45)); // NOI18N
        currencyBalance.setForeground(new java.awt.Color(255, 255, 255));
        currencyBalance.setText(formatBalance(moneyModel.getBalance()));
        currencyBalance.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 25)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Account Balance");

        withdrawButton.setBackground(Colors.cyclamen);
        withdrawButton.setText("Withdraw");
        withdrawButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                withdrawButtonActionPerformed(evt);
            }
        });

        depositButton.setBackground(Colors.mantis);
        depositButton.setText("Deposit");
        depositButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                depositButtonActionPerformed(evt);
            }
        });

        customComboBox1.setForeground(new java.awt.Color(0, 0, 0));
        customComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pesos - ₱", "Dollar - $", "Euro - €", "Yen - ¥" }));
        customComboBox1.setLabelText("Select Currency");

        customButton31.setText("Set Balance");
        customButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customButton31ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customRoundedPanel1Layout = new javax.swing.GroupLayout(customRoundedPanel1);
        customRoundedPanel1.setLayout(customRoundedPanel1Layout);
        customRoundedPanel1Layout.setHorizontalGroup(
            customRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customRoundedPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(withdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(depositButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customRoundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customRoundedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(172, 172, 172))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customRoundedPanel1Layout.createSequentialGroup()
                        .addComponent(currencySign, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(currencyBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customRoundedPanel1Layout.createSequentialGroup()
                        .addComponent(customComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customRoundedPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(customButton31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
        );
        customRoundedPanel1Layout.setVerticalGroup(
            customRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customRoundedPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(customComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customButton31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(customRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(currencySign, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyBalance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(customRoundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(withdrawButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(depositButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65))
        );

        add(customRoundedPanel1);
        customRoundedPanel1.setBounds(260, 70, 580, 380);

        jPanel1.setBackground(Colors.blueberry);

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 35)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Peddy Bank");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(437, 437, 437)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(438, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        add(jPanel1);
        jPanel1.setBounds(0, 0, 1100, 120);
    }// </editor-fold>//GEN-END:initComponents

    private void withdrawButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_withdrawButtonActionPerformed
        showTransactionDialog(transactionWindow, "Withdraw");
    }//GEN-LAST:event_withdrawButtonActionPerformed

    private void depositButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_depositButtonActionPerformed
        showTransactionDialog(transactionWindow, "Deposit");
    }//GEN-LAST:event_depositButtonActionPerformed

    private void customButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customButton31ActionPerformed
        showTransactionDialog(transactionWindow, "Update");
    }//GEN-LAST:event_customButton31ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currencyBalance;
    private javax.swing.JLabel currencySign;
    private moneymanager.customs.CustomButton3 customButton31;
    private moneymanager.customs.CustomComboBox customComboBox1;
    private moneymanager.customs.CustomRoundedPanel customRoundedPanel1;
    private moneymanager.customs.CustomButton3 depositButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private moneymanager.customs.CustomButton3 withdrawButton;
    // End of variables declaration//GEN-END:variables
}
