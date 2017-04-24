A Guice binding annotation allows you to specify a concrete
class being paired up with an annotation. 

There are 2 parts to the binding annotation. 
1) declare the annotation via an interface. 

@BindingAnnotation makesound;
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
public @interface MakeSound{
    public void makeSound();
}

@BindingAnnotation
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
public @interface Ipc {
}



2) bind a concrete class to the annotation. 
bind(SoundObject.class)annotatedWith(MakeSound.class).toInstance(soundObject.makeSound());

This reduces the boilerplate code. 
