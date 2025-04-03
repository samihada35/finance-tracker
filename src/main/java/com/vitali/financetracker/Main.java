package com.vitali.financetracker;

import model.Transaction;
import service.TransactionService;

import java.util.Scanner;

public class Main {

    private static TransactionService service = new TransactionService();

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
                service.addTransaction(type,amount,description);
            } else if (option == 2){
                 for(Transaction tr: service.getAllTransactions()){
                    System.out.println(tr);
                 }
            } else if (option == 3){
                System.out.println("Current balance: " + service.calculateBalance());
            }
            if (option == 0){
                break;
            }
        }
    }
}
