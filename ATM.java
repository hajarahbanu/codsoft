import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATM {

    private static final Map<String, BankAccount> accounts = new HashMap<>();

    public static void main(String[] args) {
        initializeAccounts();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ATM!");

        while (true) {
            System.out.print("Enter your account number: ");
            String accountNumber = scanner.nextLine();

            if (!accounts.containsKey(accountNumber)) {
                System.out.println("Invalid account number. Please try again.");
                continue;
            }

            System.out.print("Enter your PIN code: ");
            int pinCode = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            BankAccount account = accounts.get(accountNumber);

            if (account.validatePin(pinCode)) {
                performTransaction(account, scanner);
            } else {
                System.out.println("Incorrect PIN code. Access denied.");
            }
        }
    }

    private static void initializeAccounts() {
        accounts.put("123456", new BankAccount("123456", "John Doe", 1234, 1000.0));
        accounts.put("789012", new BankAccount("789012", "Jane Smith", 5678, 1500.0));
    }

    private static void performTransaction(BankAccount account, Scanner scanner) {
        while (true) {
            System.out.println("Choose an option: \n1. Check Balance \n2. Deposit \n3. Withdraw \n4. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    System.out.println("Your balance is: $" + account.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    System.out.println("Deposit successful. Your new balance is: $" + account.getBalance());
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    if (account.withdraw(withdrawAmount)) {
                        System.out.println("Withdrawal successful. Your new balance is: $" + account.getBalance());
                    } else {
                        System.out.println("Insufficient funds. Withdrawal failed.");
                    }
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Have a great day!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private int pinCode;
    private double balance;

    public BankAccount(String accountNumber, String accountHolderName, int pinCode, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.pinCode = pinCode;
        this.balance = balance;
    }

    public boolean validatePin(int enteredPin) {
        return this.pinCode == enteredPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}
