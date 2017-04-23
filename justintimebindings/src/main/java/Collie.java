import com.google.inject.Inject;

/**
 * Created by dc on 4/21/17. Given 2 ctors which on is called when you use DI since
 * you are calling a ctor with no arguments.
 */
public class Collie implements Dog{
    private String str;

    public Collie(){
        System.out.println("Default Ctor");
    }

    @Inject
    public Collie(String str){
        this.str = str;
        System.out.println("I am an injected Collie str ctor without the str argument!!!!");
    }
    public void bark(){
        System.out.println("I am a collie");
    }
    public String getStr(){
        return str;
    }
    public void setStr(String str){
        this.str = str;
    }
}
