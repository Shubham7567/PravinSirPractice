package bank;

public class CurrentAccount extends Account {

    private static final long minimumBalance = 10_000;
    //private static final long penalty = 100;
    private Penalty penalty = Penalty.DEFAULT_PENALTY;

    public CurrentAccount(long acno, String n, long openBal) throws NegativeAmountException {
        super(acno, n, openBal);
    }

    public CurrentAccount(String n, long openBal) throws NegativeAmountException {
        super(n, openBal);
    }

    public void setPenalty(Penalty p)
    {
        this.penalty = p;
    }

    public boolean withdraw(long amt) throws NegativeAmountException {
        if (!super.withdraw(amt)) {
            return false;
        }
//        if (this.balance < minimumBalance) {
        if (this.getBalance() < minimumBalance) {
//            this.balance -= penalty;
            long penalyAmount = penalty.compute(minimumBalance,this.getBalance());
            new Transaction("Penalty", TransType.DEBIT, penalyAmount);
        }
        return true;
    }
}
