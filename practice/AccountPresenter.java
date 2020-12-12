package interfaceView;

public class AccountPresenter{
    IAccountView account = new IAccountView();
    account.AccountNumber(12);
    account.Name("Shubham");
    public static void getAccount()
    {
        System.out.println("AC : "+account.getAccountNumber());
        System.out.println("Name : "+account.getName());
    }

    public static void main(String[] args)
    {
        AccountPresenter acPresenter = new AccountPresenter();
        acPresenter.getAccount();
    }
}