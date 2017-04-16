/**
 * Created by dc on 3/27/17.
 */
import com.google.inject.Inject;

class Head {

    private final Noise noise;

    @Inject
    Head(Noise noise) {
        this.noise = noise;
    }

    void speak() {
        noise.beep();
    }
}