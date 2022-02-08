package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * The RuntimeVisibleParameterAnnotations attribute.
 */
@AttributeName("RuntimeVisibleParameterAnnotations")
public final class RuntimeVisibleParameterAnnotationsAttribute extends RuntimeParameterAnnotations {

    public RuntimeVisibleParameterAnnotationsAttribute(final int attributeNameIndex, final ParameterAnnotations[] parameterAnnotations) {
        super(attributeNameIndex, parameterAnnotations);
    }

    public static RuntimeVisibleParameterAnnotationsAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final ParameterAnnotations[] parameters = new ParameterAnnotations[in.readUnsignedByte()];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = ParameterAnnotations.read(in);
        }

        return new RuntimeVisibleParameterAnnotationsAttribute(ani, parameters);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }

}
