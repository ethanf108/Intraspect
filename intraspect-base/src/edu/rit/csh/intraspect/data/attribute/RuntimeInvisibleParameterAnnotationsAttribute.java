package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * The RuntimeInvisibleParameterAnnotations attribute.
 */
@AttributeName("RuntimeInvisibleParameterAnnotationsAttribute")
public final class RuntimeInvisibleParameterAnnotationsAttribute extends RuntimeParameterAnnotations {

    public RuntimeInvisibleParameterAnnotationsAttribute(final int attributeNameIndex, final ParameterAnnotations[] parameterAnnotations) {
        super(attributeNameIndex, parameterAnnotations);
    }

    public static RuntimeInvisibleParameterAnnotationsAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final ParameterAnnotations[] parameters = new ParameterAnnotations[in.readUnsignedByte()];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = ParameterAnnotations.read(in);
        }

        return new RuntimeInvisibleParameterAnnotationsAttribute(ani, parameters);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }
}
