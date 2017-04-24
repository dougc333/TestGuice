import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Created by dc on 4/24/17.
 */
public class MyModule  extends AbstractModule{
    public void configure(){
        bind(MyObject.class);
        bindConstant().annotatedWith(Names.named("name")).to("some value");
    }
}
