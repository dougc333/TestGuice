A Guice binding annotation allows you to specify a concrete
class being paired up with an annotation. 

There are 2 parts to the binding annotation. 
1) declare the annotation: 

@BindingAnnotation makesound;
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
public @interface MakeSound{
    public void makeSound();
}

2) declare the class which has the annotation. 
bind(SoundObject.class)annotatedWith(MakeSound.class).toInstance(soundObject.makeSound());

This reduces the boilerplate code. 
