package com.vitali.financetracker;

import model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Transaction> transactions = new ArrayList<Transaction>();

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println(
                    "Choose an option:\n" +
                    "1. Add transaction\n" +
                    "2. Show all transactions\n" +
                    "3. Show current balance\n" +
                    "0. Exit"
            );
            int option = scanner.nextInt();
            if (option == 1){
                System.out.println("Type of transaction? income/expense");
                String type = scanner.next();
                System.out.println("Amount of transaction? example 22.0");
                double amount = scanner.nextDouble();
                System.out.println("Description of transaction? dollars/transport/fuel,etc");
                String description = scanner.next();
                transactions.add(new Transaction(type,amount,description));
            } else if (option == 2){
                for (Transaction tr:transactions){
                    System.out.println(tr);
                }
            } else if (option == 3){
                double currentBalance = 0;
                for (Transaction tr:transactions){
                    if(tr.getType().equalsIgnoreCase("income")){
                        currentBalance+=tr.getAmount();
                    } else {
                        currentBalance-=tr.getAmount();
                    }
                }
                System.out.println("Current balance: " + currentBalance);
            }
            if (option == 0){
                break;
            }
        }
    }
}
