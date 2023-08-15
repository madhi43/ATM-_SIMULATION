import java.util.Scanner;
interface ATMOperations {
    void withdraw(double amount) throws InsufficientFundsException;
    double checkBalance();
}
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
class ATM implements ATMOperations {
    public double balance;

    public ATM(double initialBalance) {
        balance = initialBalance;
    }
    public void updateBalance(double amount) {
        balance += amount;
    }

    public void deposit(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new InsufficientFundsException("Invalid amount for deposit.");
        }
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance");
        }
        updateBalance(-amount);
        System.out.println("Withdrawn amount : " + amount);
    }

    @Override
    public double checkBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(1000.0);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("ATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4.Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter withdrawal amount: ");
                    double amount = scanner.nextDouble();
                    try {
                        atm.withdraw(amount);
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Check Current balance: " + atm.checkBalance());
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        atm.deposit(depositAmount);
                    } catch (InsufficientFundsException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Exiting");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
