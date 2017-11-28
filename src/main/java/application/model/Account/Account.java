package application.model.Account;

public abstract class Account {

    public String accountName;
    public String accountType;




    public String getAccountName() {return accountName;}
    public void setAccountName(String accountName) {this.accountName = accountName;}
    public String getAccountType() {return accountType;}
    public void setAccountType(String accountType) {this.accountType = accountType;}
}
