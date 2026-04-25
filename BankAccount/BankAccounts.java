public abstract class BankAccounts implements Transactional {
    private String name;
    private String pin;
    protected double balance;

    public BankAccount(String name, double balance, String pin) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
    }

    public String getName(){
        return name;
    }
    public double boolean validatePin(String inputPin){
        return this.pin.equals(inputPin);
     
}
