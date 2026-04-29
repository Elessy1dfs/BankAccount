class SavingsAccount extends BankAccount {

    public SavingsAccount(String name, double balance, String pin) {
        super(name, balance, pin);
    }

    @Override
    public void withdraw(double amount, String inputPin) {

        if (!validatePin(inputPin)) {
            System.out.println("Incorrect PIN!");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else if (balance - amount < 100) {
            System.out.println("Must maintain minimum balance of $100!");
        } else {
            balance -= amount;
            history.addRecord("Withdrew: $" + amount);
            System.out.println("Withdrawal successful!");
        }
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type: SAVINGS");
    }
}
