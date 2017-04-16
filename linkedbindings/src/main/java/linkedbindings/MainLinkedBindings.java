package linkedbindings;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Created by dc on 4/12/17.
 */
public class MainLinkedBindings {
    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new BillingModule());
        TransactionLog dbtlog = injector.getInstance(TransactionLog.class);
        dbtlog.addTransaction(new Transaction("I am a record"));

    }
}
