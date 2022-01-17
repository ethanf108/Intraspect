package data.attribute;

import data.AttributeName;
import java.io.IOException;
import java.io.InputStream;
import static util.Util.readInt;

@AttributeName("RuntimeInvisibleParameterAnnotationsAttribute")
public final class RuntimeInvisibleParameterAnnotationsAttribute extends RuntimeParameterAnnotations {

    public RuntimeInvisibleParameterAnnotationsAttribute(short attributeNameIndex, ParameterAnnotations[] parameterAnnotations) {
        super(attributeNameIndex, parameterAnnotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }

    public static RuntimeInvisibleParameterAnnotationsAttribute read(short ani, InputStream in) throws IOException {
        final int length = readInt(in);
        final byte numParameters = (byte) in.read();
        final ParameterAnnotations[] parameters = new ParameterAnnotations[numParameters];
        for (int i = 0; i < numParameters; i++) {
            parameters[i] = ParameterAnnotations.read(in);
        }
        return new RuntimeInvisibleParameterAnnotationsAttribute(ani, parameters);
    }
}
