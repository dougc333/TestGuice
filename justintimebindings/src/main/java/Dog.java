import com.google.inject.ImplementedBy;

@ImplementedBy(Collie.class)
public interface Dog{
    public void bark();

}