package model;

public class Transaction {
    private String type;
    private double amount;
    private String description;

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Transaction(String type, double amount, String description) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString(){
        return type.toUpperCase() + ": " + amount + " (" + description + ")";
    }
}
