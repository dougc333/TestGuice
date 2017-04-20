https://github.com/google/guice/wiki/JustInTimeBindings

When an injector needs an instance like MyClass.class; it needs a binding to 
match the class with an instance. 

The bindings in a module are called explicit bindings. If Guice needs a type
but the type is not available, the injector can create a Just in Time binding. 
Guice creates bindings for concrete types as defined in MyClass.class
by looking at the ctor for that class, choosing a ctor with an @Inject
annotation or using the default ctor. 

Guice will NOT contruct nested classes unless they use static (test)

@ImplementedBy annotations tell Guice which class to match to a type; 

@ImplementedBy(Collie.class)
public interface Dog{
  public void bark(){};
  public void chaseStick();
  public void sleep();
  public void bite();
}
The statements above are the SAME as:
bind(Dog.class).to(Collie.class);

@ProvidedBy(Cat.class)
public interface Cat{
  public void meow();
  public void purr();
}

is the same as bind(Cat.class).toProvider(Cat.classj);

