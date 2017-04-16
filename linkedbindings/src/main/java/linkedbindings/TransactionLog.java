package linkedbindings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dc on 4/12/17.
 */
abstract class TransactionLog {
    private int balance;
    private int numTransactions;
    private List<Transaction> transactionList;


    public TransactionLog(){
        transactionList = new ArrayList<Transaction>();
        System.out.println("abstract class TransactionLog");
    }


    public void addTransaction(Transaction t){
        numTransactions++;
        System.out.println("abstract class addTransaction");
    }

    public int getBalance(){
        System.out.println("abstract class getBalance");
        return balance;
    }


}
