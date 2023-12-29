package moneymanager.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionModel {
    private static List<String> transactionHistory;

    public TransactionModel() {
        transactionHistory = new ArrayList<>();
    }

    public void addTransaction(String transaction) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        transactionHistory.add(dateFormat.format(new Date()) + " - " + transaction + "\n");
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

}
