package edu.rit.csh.intraspect.edit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstantValue {

    /**
     * @return type of constant held
     */
    Class<?> value();
}
