import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dc on 5/6/17.
 */



public class Test {

    public static void main(String[] args) {
        double inCir=0.0;

        int numRuns = 10000000;
        for (int i=0;i< numRuns;i++){
            double randomX = ThreadLocalRandom.current().nextDouble(-0.5, 0.5);
            double randomY = ThreadLocalRandom.current().nextDouble(-0.5, 0.5);
            if( (Math.pow(randomX, 2.0) + Math.pow(randomY, 2.0)) <= (Math.pow(0.5,2.0))) {
                inCir++;
            }
        }
        double result = (inCir / (double)numRuns)*4;
        System.out.println(result);
    }
}
