class CheckingAccount extends BankAccount {

    public CheckingAccount(String name, double balance, String pin) {
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
        } else {
            balance -= amount;
            history.addRecord("Withdrew: $" + amount);
            System.out.println("Withdrawal successful!");
        }
    }

    @Override
    public void displayAccountType() {
        System.out.println("Account Type: CHECKING");
    }
}
