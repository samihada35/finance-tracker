package service;

import model.Transaction;
import model.TransactionType;

import java.util.*;
import java.util.stream.Collectors;

public class StatisticService {


    public int getAllTransactionsAmount(List<Transaction> transactions) {
        int amount = 0;
        for (Transaction _ : transactions) {
            amount = Math.incrementExact(amount);
        }
        return amount;
    }

    public int getIncomeTransactionsAmount(List<Transaction> transactions) {
        int amount = 0;
        for (Transaction tr : transactions) {
            if (tr.type() == TransactionType.INCOME) {
                amount = Math.incrementExact(amount);
            }
        }
        return amount;
    }

    public int getExpenseTransactionsAmount(List<Transaction> transactions) {
        int amount = 0;
        for (Transaction tr : transactions) {
            if (tr.type() == TransactionType.EXPENSE) {
                amount = Math.incrementExact(amount);
            }
        }
        return amount;
    }


    public double getTotalIncome(List<Transaction> transactions) {
        double totalIncome = 0.0;

        for (Transaction tr : transactions) {
            if (tr.type() == TransactionType.INCOME) {
                totalIncome += tr.amount();
            }
        }
        return totalIncome;
    }

    public double getTotalExpense(List<Transaction> transactions) {
        double totalExpense = 0.0;

        for (Transaction tr : transactions) {
            if (tr.type() == TransactionType.EXPENSE) {
                totalExpense += tr.amount();
            }
        }
        return totalExpense;
    }

    public double getAvgIncome(List<Transaction> transactions) {
        double zeroIncome = 0.0;
        if (getIncomeTransactionsAmount(transactions) > 0) {
            return getTotalIncome(transactions) / getIncomeTransactionsAmount(transactions);
        } else {
            return zeroIncome;
        }
    }

    public double getAvgExpense(List<Transaction> transactions) {
        double zeroExpense = 0.0;
        if (getExpenseTransactionsAmount(transactions) > 0) {
            return getTotalExpense(transactions) / getExpenseTransactionsAmount(transactions);
        } else {
            return zeroExpense;
        }
    }

    public double getMaxIncome(List<Transaction> transactions) {
        double maxIncome = 0.0;
        for (Transaction tr : transactions) {
            if (tr.type() == TransactionType.INCOME) {
                maxIncome = Math.max(maxIncome, tr.amount());
            }
        }
        return maxIncome;
    }

    public double getMaxExpense(List<Transaction> transactions) {
        double maxExpense = 0.0;
        for (Transaction tr : transactions) {
            if (tr.type() == TransactionType.EXPENSE) {
                maxExpense = Math.max(maxExpense, tr.amount());
            }
        }
        return maxExpense;
    }

    public void getIncomeStatByDescription(List<Transaction> transactions) {
        Map<String, DoubleSummaryStatistics> statistic =
                transactions.stream()
                        .filter(c->c.type()==TransactionType.INCOME)
                        .collect(Collectors.groupingBy(
                                Transaction::description,
                                Collectors.summarizingDouble(Transaction::amount)
                        ));

        for (Map.Entry<String, DoubleSummaryStatistics> entry : statistic.entrySet()) {
            String description = entry.getKey();
            DoubleSummaryStatistics summary = entry.getValue();

            System.out.printf(
                    "Income transactions %s: %d transactions, total = %.2f, average = %.2f%n",
                    description,
                    summary.getCount(),
                    summary.getSum(),
                    summary.getAverage()
            );
        }
    }

    public void getExpenseStatByDescription(List<Transaction> transactions) {
        Map<String, DoubleSummaryStatistics> statistic =
                transactions.stream()
                        .filter(c->c.type()==TransactionType.EXPENSE)
                        .collect(Collectors.groupingBy(
                                Transaction::description,
                                Collectors.summarizingDouble(Transaction::amount)
                        ));

        for (Map.Entry<String, DoubleSummaryStatistics> entry : statistic.entrySet()) {
            String description = entry.getKey();
            DoubleSummaryStatistics summary = entry.getValue();

            System.out.printf(
                    "Expense transactions %s: %d transactions, total = %.2f, average = %.2f%n",
                    description,
                    summary.getCount(),
                    summary.getSum(),
                    summary.getAverage()
            );
        }
    }
}
