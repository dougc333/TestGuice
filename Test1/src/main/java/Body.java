/**
 * Created by dc on 3/27/17.
 */
import com.google.inject.Inject;

class Body {
    @Inject
    Body() {
    }

    void move() {
        System.out.println("We're going places");
    }
}