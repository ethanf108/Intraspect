package edu.rit.csh.intraspect.edit;

import edu.rit.csh.intraspect.data.constant.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConstantPoolIndex {

    Class<?>[] LOADABLE_TYPES = {
            IntegerConstant.class,
            LongConstant.class,
            FloatConstant.class,
            DoubleConstant.class,
            StringConstant.class,
            MethodHandleConstant.class,
            MethodTypeConstant.class,
            DynamicConstant.class
    };

    /**
     * @return array of classes this index can point to
     */
    Class<? extends ConstantDesc>[] value();

    /**
     * @return true if this index can point to 0 (no constant)
     */
    boolean nullable() default false;
}
