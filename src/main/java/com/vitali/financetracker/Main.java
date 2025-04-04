package com.vitali.financetracker;

import model.Transaction;
import service.TransactionService;
import validators.InputValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final TransactionService service = new TransactionService();

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        final List<String> menu = new ArrayList<>();
        menu.add("Choose an option:");
        menu.add("1. Add transaction");
        menu.add("2. Show all transactions");
        menu.add("3. Show current balance");
        menu.add("4. Delete transaction");
        menu.add("0. Exit");

        while (true){
            for(String str:menu){
                System.out.println(str);
            }
            int option = InputValidation.returnInteger(scanner);
            try{
                InputValidation.validateIndex(option, menu.size());
            } catch(IllegalArgumentException e){
                System.out.println("Error: "+e);
            }
            if (option == 1){
                System.out.println("Type of transaction? income/expense");
                String type = scanner.next();
                System.out.println("Amount of transaction? example 22,0");
                double amount = scanner.nextDouble();
                System.out.println("Description of transaction? dollars/transport/fuel,etc");
                String description = scanner.next();
                try{
                    service.addTransaction(type,amount,description);
                }catch(IllegalArgumentException e){
                    System.out.println("Error: "+e);
                }
            }
            else if (option == 2){
                int index = 0;
                 for(Transaction tr: service.getAllTransactions()){
                    System.out.println(index++ + ". " + tr);
                 }
            }
            else if (option == 3){
                System.out.println("Current balance: " + service.calculateBalance());
            }
            else if (option == 4){
                int index = 0;
                for(Transaction tr: service.getAllTransactions()){
                    System.out.println(index++ + ". " + tr);
                }
                System.out.println("Which transaction you want to delete?");
                int deleteIndex = scanner.nextInt();
                if (TransactionService.deleteTransaction(deleteIndex)){
                    System.out.println("transaction was deleted");
                }
            }
            if (option == 0){
                break;
            }
        }
    }
}
