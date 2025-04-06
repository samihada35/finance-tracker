package service;

import model.Transaction;
import model.TransactionType;

import java.util.List;

public class StatisticService {


    public static int getAllTransactionsAmount(List<Transaction> transactions) {
        int amount = 0;
        for (Transaction tr : transactions) {
            amount = Math.incrementExact(amount);
        }
        return amount;
    }

    public static int getIncomeTransactionsAmount(List<Transaction> transactions) {
        int amount = 0;
        for (Transaction tr : transactions) {
            if (tr.getType() == TransactionType.INCOME) {
                amount = Math.incrementExact(amount);
            }
        }
        return amount;
    }

    public static int getExpenseTransactionsAmount(List<Transaction> transactions) {
        int amount = 0;
        for (Transaction tr : transactions) {
            if (tr.getType() == TransactionType.EXPENSE) {
                amount = Math.incrementExact(amount);
            }
        }
        return amount;
    }


    public static double getTotalIncome(List<Transaction> transactions) {
        double totalIncome = 0.0;

        for (Transaction tr : transactions) {
            if (tr.getType() == TransactionType.INCOME) {
                totalIncome += tr.getAmount();
            }
        }
        return totalIncome;
    }

    public static double getTotalExpense(List<Transaction> transactions) {
        double totalExpense = 0.0;

        for (Transaction tr : transactions) {
            if (tr.getType() == TransactionType.EXPENSE) {
                totalExpense += tr.getAmount();
            }
        }
        return totalExpense;
    }

    public static double gelAvgIncome(List<Transaction> transactions) {
        double zeroIncome = 0.0;
        if (getIncomeTransactionsAmount(transactions) > 0) {
            return getTotalIncome(transactions) / getIncomeTransactionsAmount(transactions);
        } else {
            return zeroIncome;
        }
    }

    public static double gelAvgExpense(List<Transaction> transactions) {
        double zeroExpense = 0.0;
        if (getExpenseTransactionsAmount(transactions) > 0) {
            return getTotalExpense(transactions) / getExpenseTransactionsAmount(transactions);
        } else {
            return zeroExpense;
        }
    }

    public static double getMaxIncome(List<Transaction> transactions) {
        double maxIncome = 0.0;
        for (Transaction tr : transactions) {
            if (tr.getType() == TransactionType.INCOME) {
                maxIncome = Math.max(maxIncome, tr.getAmount());
            }
        }
        return maxIncome;
    }

    public static double getMaxExpense(List<Transaction> transactions) {
        double maxExpense = 0.0;
        for (Transaction tr : transactions) {
            if (tr.getType() == TransactionType.EXPENSE) {
                maxExpense = Math.max(maxExpense, tr.getAmount());
            }
        }
        return maxExpense;
    }
}
