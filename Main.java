public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount userAccount = null;

        try {
            System.out.println("--- Welcome to Java Banking ---");
            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Set a 4-digit security PIN: ");
            String securePin = sc.next();

            // Updated selection part
            System.out.println("Select Account: 1. Savings | 2. Checking");
            int type = sc.nextInt();

            // Initialize account (500.0 is the starting balance)
            userAccount = (type == 1) ? new SavingsAccount(name, 500.0, securePin)
                    : new CheckingAccount(name, 500.0, securePin);

            boolean active = true;
            while (active) {
                try {
                    System.out.println("\n1. Deposit | 2. Withdraw | 3. Balance | 4. Exit");
                    System.out.print("Choice: ");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.print("Enter deposit amount: ");
                            userAccount.deposit(sc.nextDouble());
                            break;
                        case 2:
                            System.out.print("Enter amount to withdraw: ");
                            double withdrawAmount = sc.nextDouble();

                            // Verification Step
                            System.out.print("Verification Required. Enter PIN: ");
                            String inputPin = sc.next();

                            // Call method that handles the PIN validation internally
                            userAccount.withdraw(withdrawAmount, inputPin);
                            break;
                        case 3:
                            userAccount.displayAccountType();
                            System.out.println("Available Balance: $" + userAccount.getBalance());
                            break;
                        case 4:
                            active = false;
                            System.out.println("Thank you for banking with us!");
                            break;
                        default:
                            System.out.println("Invalid selection. Please choose 1-4.");
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Error: Input must be a number.");
                    sc.next(); // Clear buffer
                }
            }
        } catch (Exception e) {
            System.err.println("Critical Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
