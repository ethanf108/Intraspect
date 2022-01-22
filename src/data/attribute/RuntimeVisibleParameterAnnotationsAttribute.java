package data.attribute;

import data.AttributeName;

import java.io.DataInputStream;
import java.io.IOException;

@AttributeName("RuntimeVisibleParameterAnnotations")
public final class RuntimeVisibleParameterAnnotationsAttribute extends RuntimeParameterAnnotations {

    public RuntimeVisibleParameterAnnotationsAttribute(final int attributeNameIndex, final ParameterAnnotations[] parameterAnnotations) {
        super(attributeNameIndex, parameterAnnotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }

    public static RuntimeVisibleParameterAnnotationsAttribute read(int ani, DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final ParameterAnnotations[] parameters = new ParameterAnnotations[in.readUnsignedByte()];
        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = ParameterAnnotations.read(in);
        }

        return new RuntimeVisibleParameterAnnotationsAttribute(ani, parameters);
    }

}
