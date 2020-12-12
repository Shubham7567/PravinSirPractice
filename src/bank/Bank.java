
package bank;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.stream.Stream;

// TODO update the class to use a Map<Long, Account> instead of Account[] to maintain all accounts of the bank
// TODO add a method called getAccountStream to return stream of Account objects

public class Bank {
    private String name;
    private long lastAccountNumber;
    //private Account[] accounts = new Account[10000];
    private Map<Long,Account> accountMap = new TreeMap<>();

    public Stream<Account> getAccountStream() {
        return this.accountMap.values().stream();
        //return Stream.of(accounts).skip(1).takeWhile(ac -> ac != null);//for array of account
    }

    public Bank(String n, int code) {
        this.name = n;
        this.lastAccountNumber = code * 10_000L;
    }
    public enum AccountType {
        CURRENT {
            @Override
            public Account create(long acno, String n, long openBal) throws NegativeAmountException {
                return new CurrentAccount(acno, n, openBal);
            }
        },
        SAVINGS {
            @Override
            public Account create(long acno, String n, long openBal) throws NegativeAmountException {
                return new SavingsAccount(acno, n, openBal);
            }
        },
        ;
        public abstract Account create(long acno, String n, long openBal) throws NegativeAmountException;
    }
    public long openAccount(AccountType type, String n, long openBal) throws NegativeAmountException {
        if (lastAccountNumber+1 % 10_000 == 0) {
            throw new RuntimeException("No more accounts allowed");
        }
        Account ac = type.create(++lastAccountNumber, n, openBal);
        //this.accounts[(int)(lastAccountNumber-1)%10_000] = ac;
        this.accountMap.put(ac.getAccountNumber(), ac);
        return ac.getAccountNumber();
    }
    private Account getAccount(long acno) throws NoSuchAccountException {
        //Account ac = this.accounts[(int)acno%10_000];
        Account ac = accountMap.get(acno);
        if (ac == null) {
            throw new NoSuchAccountException("invalid account number");
        }
        return ac;
    }
    public void deposit(long acno, long amt) throws NegativeAmountException, NoSuchAccountException {
        getAccount(acno).deposit(amt);//it returns the Account object and then call it's method
    }
    public boolean withdraw(long acno, long amt) throws NegativeAmountException, NoSuchAccountException {
        return getAccount(acno).withdraw(amt);
    }
    public void display(long acno) throws NoSuchAccountException {
        getAccount(acno).display();
    }
    public void printPassbook(long acno) throws NoSuchAccountException {
        getAccount(acno).printPassbook();
    }
    public void listAccounts() {
        System.out.println("List of Accounts for bank:"+this.name);
        for (Account account : accountMap.values()) {
            if (account == null) {
                break;
            }
            account.display();
        }
        System.out.println("End of account list");
    }
}

