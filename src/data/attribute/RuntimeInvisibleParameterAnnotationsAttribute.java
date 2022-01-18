package data.attribute;

import data.AttributeName;
import java.io.DataInputStream;
import java.io.IOException;

@AttributeName("RuntimeInvisibleParameterAnnotationsAttribute")
public final class RuntimeInvisibleParameterAnnotationsAttribute extends RuntimeParameterAnnotations {

    public RuntimeInvisibleParameterAnnotationsAttribute(int attributeNameIndex, ParameterAnnotations[] parameterAnnotations) {
        super(attributeNameIndex, parameterAnnotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }

    public static RuntimeInvisibleParameterAnnotationsAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();
        final int numParameters = in.readUnsignedByte();
        final ParameterAnnotations[] parameters = new ParameterAnnotations[numParameters];
        for (int i = 0; i < numParameters; i++) {
            parameters[i] = ParameterAnnotations.read(in);
        }
        return new RuntimeInvisibleParameterAnnotationsAttribute(ani, parameters);
    }
}
