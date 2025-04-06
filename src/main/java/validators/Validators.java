package validators;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Validators {
    public static void validateTransaction(String type, double amount, String description) {
        if (!(type.equalsIgnoreCase("income") || type.equalsIgnoreCase("expense"))) {
            throw new IllegalArgumentException("type must be filled with 'income' or 'expense' text only");
        } else if (isEmptyString(description)) {
            throw new IllegalArgumentException("description must be filled with data");
        } else if (!isPositiveDouble(amount)) {
            throw new IllegalArgumentException("amount must be > 0");
        }
    }

    public static void validateIndex(int index, int collectionSize) {
        if (!notNegativeInt(index) || index > collectionSize - 1) {
            throw new IllegalArgumentException("incorrect index, must exist in transaction list");
        }
    }


    public static boolean notNegativeInt(int value) {
        return value >= 0;
    }

    public static boolean isPositiveDouble(double value) {
        return value > 0;
    }

    public static boolean isEmptyString(String str) {
        return str == null || str.isBlank();
    }

    public static int returnInteger(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Not an integer value, please try again.");
                scanner.nextInt();
            }
        }
    }
}
