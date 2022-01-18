package data.attribute;

import data.AttributeName;
import data.attribute.annotation.AnnotationDesc;
import java.io.IOException;
import java.io.DataInputStream;
import static util.Util.readInt;
import static util.Util.readShort;

@AttributeName("RuntimeInvisibleAnnotations")
public final class RuntimeInvisibleAnnotationsAttribute extends RuntimeAnnotations {

    public RuntimeInvisibleAnnotationsAttribute(short attributeNameIndex, AnnotationDesc[] annotations) {
        super(attributeNameIndex, annotations);
    }

    public static RuntimeInvisibleAnnotationsAttribute read(short ani, DataInputStream in) throws IOException {
        final int length = readInt(in);
        final short numAnnotations = readShort(in);
        final AnnotationDesc[] annotations = new AnnotationDesc[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = AnnotationDesc.read(in);
        }
        return new RuntimeInvisibleAnnotationsAttribute(ani, annotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }
}
