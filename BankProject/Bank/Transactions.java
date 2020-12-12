package bank;

import java.util.Date;

public class Transactions
{
    private double amount;
    private double running_balance;
    private Date date;
    private enum TransType {
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

    //setters
    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    public void setRunningBalance(double balance)
    {
        this.running_balance = balance + (TransType.getSign() * amount);
    }

    public void 
}