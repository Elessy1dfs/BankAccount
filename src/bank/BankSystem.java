import java.util.ArrayList;

public class BankSystem {
    private ArrayList<BankAccount> accounts;

    public BankSystem(){
        this.accounts = new ArrayList<BankAccount>();
    }
    
    //function para mo add og account
    public void addAccount(BankAccount account){
        this.accounts.add(account);
    }

    //function para mo pangita sa account
    public BankAccount findAccount(String name){
        for(int i = 0; i < this.accounts.size(); i++){
            BankAccount currentAccount = this.accounts.get(i);

            if(currentAccount.getName().equals(name)){
                return currentAccount;
            }
        }

        return null;
    }


}
