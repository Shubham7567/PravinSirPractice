public interface interfacefunc
{
    int number = 5;
    public static int getNumber()
    {
        return number;
    }
    
    public static int giveNumber()
    {
        return number;
    }

    public static void main(String[] args)
    {
        System.out.println("Hello " + getNumber());
    }
}