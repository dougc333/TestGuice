/**
 * Created by dc on 3/27/17.
 */
class LoudNoise implements Noise {
    final String text;

    LoudNoise(String text) {
        this.text = text;
    }

    @Override
    public void beep() {
        System.out.println(text);
    }
}