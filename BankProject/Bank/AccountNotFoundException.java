package bank;

public class AccountNotFoundException extends Exception
{
    private double amount;
    private Account account;

    AccountNotFoundException(String message, double amount, Account account)
    {
        super(msg);
        this.amount = amount;
        this.account = account;
    }

    public double getAmount()
    {
        return amount;
    }

    public Account getAccount()
    {
        return account;
    }

    public String toString()
    {
        return super.toString() + " : " + amount + " : " + account;
    }
}