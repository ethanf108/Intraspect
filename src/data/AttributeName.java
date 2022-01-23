package data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation representing the formal name of an attribute.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AttributeName {

    /**
     * Returns the formal name of the attribute.
     *
     * @return the formal name of the attribute.
     */
    String value();
}
