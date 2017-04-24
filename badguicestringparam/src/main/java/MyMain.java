import com.google.inject.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by dc on 4/24/17.
 */
public class MyMain {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());

        MyObject myObject = injector.getInstance(MyObject.class);
        System.out.println(myObject.getName());

        Map<Key<?>, Binding<?>> map = injector.getAllBindings();
        System.out.println("map size:"+map.size());
        map.forEach((key, value) -> {
            System.out.println("Key : " + key + " Value : " + value);
        });
    }
}
