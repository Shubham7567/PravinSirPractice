package bank;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

/*
An abstract class to represent a bank account. It has methods for deposit, withdraw, toString, display.
Two Account instances are considered equal if their account numbers match.
TODO Override the equals and hashCode methods 
TODO use a List<Transaction> instead of the array for the passbook
TODO add a method called getPassbook to return a List<Transaction>
TODO implement the Comparable interface
TODO use LocalDate class from the java.time package instead of long for the date in Transaction class
TODO add the method called getTransactionStream to return Stream<Transaction>
TODO add a method called hasPenalty to return true if the passbook contains a penalty entryTODO try to get rid of the balance field from the Account class, change the implementation of getBalance method to return balance from the List of Transaction.
*/
public abstract class Account implements Comparable<Account> {

    private long accountNumber;
    private String name;
//    long balance;
    private long balance;

    //private Transaction[] passbook = new Transaction[100];
    private List<Transaction> passbook = new ArrayList<Transaction>();
    private int nextIndexInPassbook = 0;

    public Stream<Transaction> getTransactionStream() {
        return this.passbook.stream();
    }

    public boolean hasPenalty()
    {
        return this.passbook.stream().map(Transaction::getNaration).anyMatch(n -> n.equalsIgnoreCase("Penalty"));
    }

    public class Transaction {
        private long date = System.currentTimeMillis();
        private String naration;
        private TransType transType;
        private long amount;
        private long runningBalance;

        public Transaction(String n, TransType t, long amt) throws NegativeAmountException {
            if (amt < 0) {
                throw new NegativeAmountException("Negative "+transType.toString().toLowerCase(), amt, Account.this);
            }
            this.naration = n;
            this.transType = t;
            this.amount = amt;
            Account.this.balance += getNetAmount();
            this.setRunningBalance();
            //Account.this.passbook[nextIndexInPassbook++] = this;
            Account.this.passbook.add(this);

        }

        public long getDate() {
            return this.date;
        }

        public String getNaration() {
            return this.naration;
        }

        public long getAmount() {
            return this.amount;
        }

        public long getNetAmount() {
            return this.transType.getSign()*this.getAmount();
        }

        public String toString() {
            return "Transaction:"+String.format("%tF, %15s, %10s, %12d", this.date, this.naration, this.transType.toString(), this.amount);
        }

        public void displayRecord()
        {
            System.out.println("Transaction:"+String.format("%tF, %15s, %10s, %12d, %12d", this.date, this.naration, this.transType.toString(), this.amount,this.runningBalance));
        }

        public void setRunningBalance()
        {
            this.runningBalance = Account.this.balance;
        }

        public long getRunningBalance()
        {
            return this.runningBalance;
        }
    }   // end of inner class Transaction

    public enum TransType {
        CREDIT(1),
        DEBIT(-1),
        ;
        private int sign;
        TransType(int sign) { 
            this.sign = sign; 
        }
        public int getSign() {
            return this.sign;
        }
    }   // end of enum TransType

    public void printPassbook() {
        long runningBalance = 0;
        //Transaction t = null;
        System.out.println("Passbook of "+name+" Account # "+accountNumber);
        /*for (int transactionIndex = 0; transactionIndex < nextIndexInPassbook; transactionIndex++) {
            t = passbook[transactionIndex];
            runningBalance += t.getNetAmount();
            System.out.println(t+","+runningBalance);
        }*/
        for(Transaction tr : passbook)
        {
            runningBalance += tr.getNetAmount();
            System.out.println(tr+","+runningBalance);
        }
        System.out.println("End of Passbook");
    }

    public List<Transaction> getPassbook() {
        // long runningBalance = 0;
        // Transaction t = null;
        // System.out.println("Passbook of "+name+" Account # "+accountNumber);
        // for(Transaction tr : passbook)
        // {
        //     runningBalance += tr.getNetAmount();
        //     System.out.println(tr+","+runningBalance);
        // }
        // System.out.println("End of Passbook");
        return passbook;
    }


    public Account(long acno, String n, long openBal) throws NegativeAmountException {
/*
        if (openBal < 0) {
            throw new NegativeAmountException("Negative open balance", openBal, this);
        }
*/
        this.accountNumber = acno;
        this.name = n;
//        this.balance = openBal;
        new Transaction("Opening Balance", TransType.CREDIT, openBal);
    }

    public long getAccountNumber() {
        return this.accountNumber;
    }

    public String getName() {
        return this.name;
    }

    public long getBalance() {
        return this.balance;
    }

    public void deposit(long amt) throws NegativeAmountException {
/*
        if (amt < 0) {
            throw new NegativeAmountException("Negative deposit", amt, this);
        }
*/
//        this.balance += amt;
        new Transaction("Deposit", TransType.CREDIT, amt);
    }

    public boolean withdraw(long amt) throws NegativeAmountException {
/*
        if (amt < 0) {
            throw new NegativeAmountException("Negative withdraw", amt, this);
        }
*/
        if (this.balance < amt) {
            return false;
        }
//        this.balance -= amt;
        new Transaction("Withdrawal", TransType.DEBIT, amt);
        return true;
    }

    public void display() {
//        System.out.println("Account:"+this.accountNumber+","+this.name+","+this.balance);
        System.out.println(this);
    }

    private static long lastAccountNumber = 1000;

    public Account(String n, long openBal) throws NegativeAmountException {
        this(++lastAccountNumber, n, openBal);
    }

    @Override
    public String toString() {
        return this.getClass().getName()+":"+this.getAccountNumber()+","+this.getName()+","+this.getBalance();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if(obj.getClass() != this.getClass())
            return false;//return this.getAccountNumber() == ((Account)obj).getAccountNumber();
        //return true;
        return this.getAccountNumber() == ((Account)obj).getAccountNumber();
    }
    @Override
    public int hashCode() {
        return (int)this.getAccountNumber();
    }
    @Override
    public int compareTo(Account ac) {
        return ((Long)(this.getAccountNumber())).compareTo(ac.getAccountNumber());
    }

    public void DisplayPassbook()
    {
        passbook.forEach(Transaction::displayRecord);
    }
}

