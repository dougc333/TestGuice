/**
 * Created by dc on 3/27/17.
 */
import com.google.inject.Inject;

class SilentNoise implements Noise {
    @Inject
    SilentNoise() {
    }

    @Override
    public void beep() {
        // no op
    }
}