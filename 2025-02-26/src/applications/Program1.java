package applications;

import entities.Account;
import exceptions.BusinessException;

import java.util.Scanner;

public class Program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter account data: ");
        System.out.print("Number: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Holder: ");
        String holder = sc.nextLine();

        System.out.print("Initial Balance: ");
        double initialBalance = sc.nextDouble();

        System.out.print("Withdraw Limit: ");
        double withdrawLimit = sc.nextDouble();

        Account account = new Account(number, holder, initialBalance, withdrawLimit);

        try {
            System.out.print("\nEnter amount to deposit: ");
            double depositAmount = sc.nextDouble();
            account.deposit(depositAmount);
            System.out.println("New balance: " + account.getBalance());

            System.out.print("\nEnter amount to withdraw: ");
            double withdrawAmount = sc.nextDouble();
            account.withdraw(withdrawAmount);
            System.out.println("New balance: " + account.getBalance());

        } catch (BusinessException e) {
            System.out.println("Transaction error — " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error — " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
