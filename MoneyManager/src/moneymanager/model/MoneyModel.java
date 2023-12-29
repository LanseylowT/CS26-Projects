package moneymanager.model;

public class MoneyModel {
    private double balance;
    private String currencySign;
    private int value;
    private int newValue;

    // region Getters and Setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrencySign() {
        return currencySign;
    }

    public void setCurrencySign(String currencySign) {
        this.currencySign = currencySign;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNewValue() {
        return newValue;
    }

    public void setNewValue(int newValue) {
        this.newValue = newValue;
    }

    public MoneyModel(double balance, String currencySign, int value, int newValue) {
        this.balance = balance;
        this.currencySign = currencySign;
        this.value = value;
        this.newValue = newValue;
    }

    // region

}
