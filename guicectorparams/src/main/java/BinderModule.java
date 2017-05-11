import com.google.inject.AbstractModule;
import com.google.inject.Binder;
import com.google.inject.PrivateModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

class BinderModule extends AbstractModule {

    @Override
    public void configure(){
        System.out.println("configure");
        bind(App.class);
        install(new FactoryModuleBuilder()
        .implement(FooInterface.class, Foo.class)
        .build(FooFactory.class));
        install(new PrivateModule() {
                    @Override
                    protected void configure() {

                    }
                }


        );
    }

}
