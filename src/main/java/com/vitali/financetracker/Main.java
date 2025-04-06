package com.vitali.financetracker;

import model.Transaction;
import service.StatisticService;
import service.TransactionService;
import validators.Validators;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final TransactionService transactionService = new TransactionService();
    private static final StatisticService statisticService = new StatisticService();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        final List<String> menu = createMenu();

        while (true) {
            for (String str : menu) {
                printStr(str);
            }
            int option = Validators.returnInteger(scanner);

            try {
                Validators.validateIndex(option, menu.size());
            } catch (IllegalArgumentException e) {
                printStr("Error: " + e);
            }

            if (option == 1) {
                printStr("Type of transaction? income/expense");
                String type = scanner.next();
                printStr("Amount of transaction? example 22,0");

                double amount = scanner.nextDouble();
                printStr("Description of transaction? dollars/transport/fuel,etc");
                String description = scanner.next();
                try {
                    transactionService.addTransaction(type, amount, description);
                } catch (IllegalArgumentException e) {
                    printStr("Error: " + e);
                }
            }
            else if (option == 2) {
                int index = 0;
                for (Transaction tr : transactionService.getAllTransactions()) {
                    printStr(index++ + ". " + tr);
                }
                statisticService.getIncomeStatByDescription(transactionService.getAllTransactions());
                statisticService.getExpenseStatByDescription(transactionService.getAllTransactions());
            }
            else if (option == 3) {
                printStr("Current balance: " + transactionService.calculateBalance());
            }
            else if (option == 4) {
                int index = 0;
                for (Transaction tr : transactionService.getAllTransactions()) {
                    printStr(index++ + ". " + tr);
                }
                printStr("Which transaction you want to delete?");
                try {
                    int deleteIndex = scanner.nextInt();
                    if (transactionService.deleteTransaction(deleteIndex)) {
                        printStr("transaction was deleted");
                    }
                } catch (IllegalArgumentException e) {
                    printStr("Error: " + e);
                }
            }
            else if (option == 5) {
                printStr("Total transactions: " + statisticService.getAllTransactionsAmount(transactionService.getAllTransactions()));
                printStr("Total income: " + statisticService.getTotalIncome(transactionService.getAllTransactions()));
                printStr("Total expense: " + statisticService.getTotalExpense(transactionService.getAllTransactions()));
                printStr("Average income: " + statisticService.getAvgIncome(transactionService.getAllTransactions()));
                printStr("Average expense: " + statisticService.getAvgExpense(transactionService.getAllTransactions()));
                printStr("Max income: " + statisticService.getMaxIncome(transactionService.getAllTransactions()));
                printStr("Max expense: " + statisticService.getMaxExpense(transactionService.getAllTransactions()));
            }
            else if (option == 0) {
                break;
            }
        }
        scanner.close();
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
    private static void printStr(String str){
        System.out.println(str);
    }
}
