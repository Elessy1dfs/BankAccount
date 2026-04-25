public abstract class BankAccounts implements Transactional {
    private String name;
    private String pin;
    protected double balance;

    public BankAccount(String name, double balance, String pin) {
        this.name = name;
        this.balance = balance;
        this.pin = pin;
    }

 
}
