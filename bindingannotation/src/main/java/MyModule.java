import com.google.inject.AbstractModule;

/**
 * Created by dc on 4/24/17.
 */
public class MyModule extends AbstractModule {
    public void configure(){
        bind(MyObject.class);
        bindConstant().annotatedWith(Color.class).to("blue");

    }
}
