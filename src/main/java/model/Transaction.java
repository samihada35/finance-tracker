package model;

public class Transaction {
    private final TransactionType type;
    private final double amount;
    private final String description;

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public Transaction(TransactionType type, double amount, String description) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    @Override
    public String toString() {
        return type.toString().toUpperCase() + ": " + amount + " (" + description + ")";
    }
}
