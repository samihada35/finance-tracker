package com.vitali.financetracker;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args){
        transactions.add(new Transaction("income", 1200.0, "salary"));
        transactions.add(new Transaction("expense", 200.0, "Groceries"));
        transactions.add(new Transaction("expense", 120.3, "Transport"));

        double currentBalance = 0.0;

        for (Transaction tr:transactions){
            System.out.println(tr);
            if(tr.getType().equalsIgnoreCase("income")){
                currentBalance+=tr.getAmount();
            } else {
                currentBalance-=tr.getAmount();
            }
        }
        System.out.println("---");
        System.out.println("Current balance: " + currentBalance);
    }
}
