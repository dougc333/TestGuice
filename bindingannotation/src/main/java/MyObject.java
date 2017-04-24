import com.google.inject.Inject;

/**
 * Created by dc on 4/24/17.
 */
public class MyObject {
    private String color;

    @Inject
    public MyObject(@Color String color){
        this.color = color;
    }

    public String getColor(){
        return color;
    }

}
