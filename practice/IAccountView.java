package interfaceView;

public interface IAccountView {
    public static final int AccountNumber = (number) -> number;
    public static final String Name = (name) -> name;

    public static int getAccountNumber()
    {
        return AccountNumber;
    }

    public static String getName()
    {
        return Name;
    }
}
