/**
 * Created by dc on 3/27/17.
 */
import com.google.inject.Inject;

class Robot {

    private final Head head;
    private final Body body;

    @Inject
    Robot(Head head, Body body) {
        this.head = head;
        this.body = body;
    }

    void doStuff() {
        head.speak();
        body.move();
    }
}