package service;

import model.Transaction;
import model.TransactionType;
import validators.InputValidation;

import java.util.ArrayList;
import java.util.List;


public class TransactionService {
    private static final List<Transaction> transactions = new ArrayList<Transaction>();

    public void addTransaction(String type, double amount, String description){
        InputValidation.validateTransaction(type,amount,description);
        if(type.equalsIgnoreCase("income")){
            transactions.add(new Transaction(TransactionType.INCOME,amount,description));
        }
        else if(type.equalsIgnoreCase("expense")){
            transactions.add(new Transaction(TransactionType.EXPENSE,amount,description));
        }
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
            if(tr.getType().equals(TransactionType.INCOME)){
                currentBalance+=tr.getAmount();
            }
            else if (tr.getType().equals(TransactionType.EXPENSE)) {
                currentBalance-=tr.getAmount();
            }
        }
        return currentBalance;
    }
}
