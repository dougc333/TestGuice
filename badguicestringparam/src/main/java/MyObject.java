import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Created by dc on 4/24/17.
 */
public class MyObject {
    private String name;
    @Inject
    public MyObject(@Named("name") String name){
        System.out.println("Guice calling MyObject ctor");
        this.name = name;
    }
    public String getName(){
        return name;
    }
}
