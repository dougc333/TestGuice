import com.google.inject.AbstractModule;

public class AppModule extends AbstractModule{
    public void configure(){
        // bind or install?
        bind(Dog.class); //note we didnt have to add a bind(Dog.class).to(Collie.class) big deal

    }

}