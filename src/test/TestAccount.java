
package test;

import java.util.Arrays;
import java.util.Comparator;
import static java.util.Comparator.*;
import bank.*;

public class TestAccount {
    private static void display(Account[] accounts) {
        for (Account ac : accounts) {
            ac.display();
        }
    }
    public static void main(String[] args) throws NegativeAmountException {
        CurrentAccount ca = new CurrentAccount("James", 1_00_000);
        System.out.println("before penalty");
        ca.display();
// TODO set the penalty function on ca according to the function
/*
(minBal, bal) -> {
                    long diff = minBal - bal;
                    if (diff <= 0) return 0;
                    if (diff <= 1_000) return 100;
                    if (diff <= 2_000) return 200;
                    if (diff <= 3_000) return 300;
                    return 500;
                }
*/
// TODO then withdraw to get the penalty applied on the object ca.
        ca.withdraw(91_000);
        ca.display();
        ca.setPenalty(Penalty.percent(0.10));
        //ca.getPassbook();
        System.out.println("Passbook of "+ca.getName()+" Account # "+ca.getAccountNumber());
        ca.DisplayPassbook();
        System.out.println("End of Passbook");
        Account[] accounts = new Account[5];
        try {
            accounts[0] = new SavingsAccount(12321, "John", 1_00_000);
            accounts[1] = new CurrentAccount(23123, "jane", 1_00_000);
            accounts[2] = new CurrentAccount(12312, "John", 50_000);
            accounts[3] = new SavingsAccount(33411, "Joseph", 1_50_000);
            accounts[4] = new CurrentAccount(23344, "Janet", 75_000);
            System.out.println("before sorting");
            display(accounts);
        } catch (NegativeAmountException nae) {
            nae.printStackTrace();
        }
// TODO create a comparator to order by name, then sort and display
        Comparator<Account> nameComparator = Comparator.comparing(Account::getName);
        Arrays.sort(accounts,nameComparator);
        System.out.println("after sorting by name");
        display(accounts);
// TODO create a comparator to order by balances, then sort and display
        Comparator<Account> balanceComparator = Comparator.comparing(Account::getBalance);
        Arrays.sort(accounts,balanceComparator);
        System.out.println("after sorting by balance");
        display(accounts);
// TODO create a comparator to order by the natural ordering of Account, ie. according to Comparable<Account>
        Comparator<Account> accountNoComparator = Comparator.naturalOrder();
        Arrays.sort(accounts,accountNoComparator);
        System.out.println("after sorting by natural order");
        display(accounts);
// TODO create a comparator to order by the reverse ordering of Account, ie. according to Comparable<Account>
        Arrays.sort(accounts,Collections.reverseOrder());
        System.out.println("after sorting by reverse order");
        display(accounts);
// TODO create a comparator to order by name reversed, then display then
        System.out.println("after sorting by name reversed");
        display(accounts);
// TODO create a comparator to order by name ignoring the case, then display
        System.out.println("after sorting by name case insensitive");
        display(accounts);
// TODO sort the array by name and then on balances.
        System.out.println("after sorting by name then on balance");
        display(accounts);
// TODO create a Comparator by Account type. then sort them by balance and display
        System.out.println("after sorting by type then on balance");
        display(accounts);
    }
}

