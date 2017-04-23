

class Foo implements FooInterface{
    String bar;

    @Inject
    Foo(@Assisted String bar){
        this.bar = bar;
    }

    String getFooName(){
        return bar;
    }

}
