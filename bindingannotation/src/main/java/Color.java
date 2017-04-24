import com.google.inject.BindingAnnotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.PARAMETER;

/**
 * Created by dc on 4/24/17.
 */


@BindingAnnotation
@Target({PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Color {

}
