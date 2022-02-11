package edu.rit.csh.intraspect.edit;

import edu.rit.csh.intraspect.data.constant.ConstantDesc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstantPoolIndex {

    /**
     * @return array of classes this index can point to
     */
    Class<? extends ConstantDesc>[] value();

    /**
     * @return true if this index can point to 0 (no constant)
     */
    boolean nullable() default false;
}
