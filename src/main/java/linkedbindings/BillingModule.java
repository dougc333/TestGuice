package linkedbindings;

import com.google.inject.AbstractModule;


/**
 * Created by dc on 4/12/17.
 */
public class BillingModule extends AbstractModule {
    public void configure(){
        bind(TransactionLog.class).to(DatabaseTransactionLog.class);
    }
}
