package bank;

@FunctionalInterface
public interface Penalty
{
    public long compute(long minBal,long balance);
    public static final Penalty ZERO_PENALTY = (minBal,balance) -> 0;
    public static final Penalty DEFAULT_PENALTY = (minBal,balance) -> balance < minBal ? 100 : 0;
    public static Penalty fixed(long penaltyAmount){
        return (minBal,balance) -> balance < minBal ? penaltyAmount : 0;
    }
    public static Penalty percent(double percentage){
        return (minBal,balance) -> {
            long diff = minBal - balance;
            if(diff >= 0) return 0;
            return (long)(diff * percentage);
        };
    }
}