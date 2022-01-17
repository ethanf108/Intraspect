package data.attribute;

import data.AttributeName;
import java.io.IOException;
import java.io.InputStream;
import static util.Util.readInt;

@AttributeName("RuntimeVisibleParameterAnnotations")
public final class RuntimeVisibleParameterAnnotationsAttribute extends RuntimeParameterAnnotations {

    public RuntimeVisibleParameterAnnotationsAttribute(short attributeNameIndex, ParameterAnnotations[] parameterAnnotations) {
        super(attributeNameIndex, parameterAnnotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }

    public static RuntimeVisibleParameterAnnotationsAttribute read(short ani, InputStream in) throws IOException {
        final int length = readInt(in);
        final byte numParameters = (byte) in.read();
        final ParameterAnnotations[] parameters = new ParameterAnnotations[numParameters];
        for (int i = 0; i < numParameters; i++) {
            parameters[i] = ParameterAnnotations.read(in);
        }
        return new RuntimeVisibleParameterAnnotationsAttribute(ani, parameters);
    }

}
