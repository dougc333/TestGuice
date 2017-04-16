/**
 * Created by dc on 3/27/17.
 */

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;



public class GuiceDemo {
    public static void main(String[] args) {
        System.out.println("sgsgsgs");

        Injector injector = Guice.createInjector(new RobotModule());

        Robot robot = injector.getInstance(Robot.class);

        robot.doStuff();
    }

    static class RobotModule extends AbstractModule {
        @Override
        protected void configure() {
            binder().requireExplicitBindings();
            binder().requireAtInjectOnConstructors();
            binder().requireExactBindingAnnotations();

            bind(Robot.class);
            bind(Head.class);
            bind(Body.class);

//            bind(Noise.class).toInstance(new LoudNoise("yo"));
            bind(Noise.class).to(SilentNoise.class);
        }
    }

}
