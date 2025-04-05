package service;

import model.Transaction;
import validators.InputValidation;

import java.util.ArrayList;
import java.util.List;


public class TransactionService {
    private static final List<Transaction> transactions = new ArrayList<Transaction>();

    public void addTransaction(String type, double amount, String description){
        InputValidation.validateTransaction(type,amount,description);
        transactions.add(new Transaction(type,amount,description));
    }

    public static boolean deleteTransaction(int index){
        InputValidation.validateIndex(index, transactions.size());
        transactions.remove(index);
        return true;
    }

    public List<Transaction> getAllTransactions(){
        return transactions;
    }

    public double calculateBalance(){
        double currentBalance = 0;
        for (Transaction tr:transactions){
            if(tr.getType().equalsIgnoreCase("income")){
                currentBalance+=tr.getAmount();
            } else {
                currentBalance-=tr.getAmount();
            }
        }
        return currentBalance;
    }
}
