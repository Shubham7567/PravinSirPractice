package bank;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Account
{
    private long account_no;
    private String name;
    private String phone_no;
    private double balance;
    private List<Transactions> passbook = new ArrayList<>();
}