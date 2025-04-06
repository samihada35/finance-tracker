package model;

public record Transaction(TransactionType type, double amount, String description) {

    @Override
    public String toString() {
        return type.toString().toUpperCase() + ": " + amount + " (" + description + ")";
    }
}
