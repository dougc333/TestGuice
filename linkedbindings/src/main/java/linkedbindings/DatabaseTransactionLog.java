package linkedbindings;

/**
 * Created by dc on 4/12/17.
 */
public class DatabaseTransactionLog extends TransactionLog {

    public DatabaseTransactionLog(){
        super();
        System.out.println("DataBaseTransactionLog ctor");
    }

    public void addTransaction(Transaction t){
        super.addTransaction(new Transaction("DatabaseTransactionLog new Transaction"));
        System.out.println("DatabaseTransactionLog addTransaction");
    }

}
