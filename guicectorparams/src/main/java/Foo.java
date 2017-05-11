import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

class Foo implements FooInterface{
    String bar;


    @Inject
    public Foo(@Assisted String bar){
        System.out.println("calling Foo bar ctor");
        this.bar = bar;
    }

    public String getFooName(){
        return bar;
    }


}

