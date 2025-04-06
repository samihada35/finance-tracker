package service;

import model.Transaction;
import model.TransactionType;
import validators.Validators;

import java.util.ArrayList;
import java.util.List;


public class TransactionService {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(String type, double amount, String description) {
        Validators.validateTransaction(type, amount, description);
        if (type.equalsIgnoreCase("income")) {
            transactions.add(new Transaction(TransactionType.INCOME, amount, description));
        } else if (type.equalsIgnoreCase("expense")) {
            transactions.add(new Transaction(TransactionType.EXPENSE, amount, description));
        }
    }

    public boolean deleteTransaction(int index) {
        Validators.validateIndex(index, transactions.size());
        transactions.remove(index);
        return true;
    }

    public List<Transaction> getAllTransactions() {
        return transactions;
    }

    public double calculateBalance() {
        double currentBalance = 0;
        for (Transaction tr : transactions) {
            if (tr.type().equals(TransactionType.INCOME)) {
                currentBalance += tr.amount();
            } else if (tr.type().equals(TransactionType.EXPENSE)) {
                currentBalance -= tr.amount();
            }
        }
        return currentBalance;
    }
}
