package data.attribute;

import data.AttributeName;
import java.io.DataInputStream;
import java.io.IOException;

@AttributeName("RuntimeVisibleParameterAnnotations")
public final class RuntimeVisibleParameterAnnotationsAttribute extends RuntimeParameterAnnotations {

    public RuntimeVisibleParameterAnnotationsAttribute(int attributeNameIndex, ParameterAnnotations[] parameterAnnotations) {
        super(attributeNameIndex, parameterAnnotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }

    public static RuntimeVisibleParameterAnnotationsAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();
        final int numParameters = in.readUnsignedByte();
        final ParameterAnnotations[] parameters = new ParameterAnnotations[numParameters];
        for (int i = 0; i < numParameters; i++) {
            parameters[i] = ParameterAnnotations.read(in);
        }
        return new RuntimeVisibleParameterAnnotationsAttribute(ani, parameters);
    }

}
