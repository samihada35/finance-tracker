package service;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private static List<Transaction> transactions = new ArrayList<Transaction>();

    public void addTransaction(String type, double amount, String description){
        transactions.add(new Transaction(type,amount,description));
    }

    public void getAllTransactions(){
        for(Transaction tr: transactions){
            System.out.println(tr);
        }
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
