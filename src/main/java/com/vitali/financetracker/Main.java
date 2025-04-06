package com.vitali.financetracker;

import model.Transaction;
import service.StatisticService;
import service.TransactionService;
import validators.Validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final TransactionService service = new TransactionService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final List<String> menu = createMenu();

        while (true) {
            for (String str : menu) {
                System.out.println(str);
            }
            int option = Validators.returnInteger(scanner);

            try {
                Validators.validateIndex(option, menu.size());
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e);
            }

            if (option == 1) {
                System.out.println("Type of transaction? income/expense");
                String type = scanner.next();
                System.out.println("Amount of transaction? example 22,0");

                double amount = scanner.nextDouble();
                System.out.println("Description of transaction? dollars/transport/fuel,etc");
                String description = scanner.next();
                try {
                    service.addTransaction(type, amount, description);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e);
                }
            }
            else if (option == 2) {
                int index = 0;
                for (Transaction tr : service.getAllTransactions()) {
                    System.out.println(index++ + ". " + tr);
                }
            }
            else if (option == 3) {
                System.out.println("Current balance: " + service.calculateBalance());
            }
            else if (option == 4) {
                int index = 0;
                for (Transaction tr : service.getAllTransactions()) {
                    System.out.println(index++ + ". " + tr);
                }
                System.out.println("Which transaction you want to delete?");
                try {
                    int deleteIndex = scanner.nextInt();
                    if (TransactionService.deleteTransaction(deleteIndex)) {
                        System.out.println("transaction was deleted");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e);
                }
            }
            else if (option == 5) {
                System.out.println("Total transactions: " + StatisticService.getAllTransactionsAmount(service.getAllTransactions()));
                System.out.println("Total income: " + StatisticService.getTotalIncome(service.getAllTransactions()));
                System.out.println("Total expense: " + StatisticService.getTotalExpense(service.getAllTransactions()));
                System.out.println("Average income: " + StatisticService.gelAvgIncome(service.getAllTransactions()));
                System.out.println("Average expense: " + StatisticService.gelAvgExpense(service.getAllTransactions()));
                System.out.println("Max income: " + StatisticService.getMaxIncome(service.getAllTransactions()));
                System.out.println("Max expense: " + StatisticService.getMaxExpense(service.getAllTransactions()));
            } else if (option == 0) {
                break;
            }
        }
    }

    private static List<String> createMenu() {
        final List<String> menu = new ArrayList<>();
        menu.add("Choose an option:");
        menu.add("1. Add transaction");
        menu.add("2. Show all transactions");
        menu.add("3. Show current balance");
        menu.add("4. Delete transaction");
        menu.add("5. Show statistics");
        menu.add("0. Exit");
        return menu;
    }
}
