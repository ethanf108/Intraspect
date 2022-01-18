package data.attribute;

import data.AttributeName;
import data.attribute.annotation.AnnotationDesc;
import java.io.DataInputStream;
import java.io.IOException;

@AttributeName("RuntimeInvisibleAnnotations")
public final class RuntimeInvisibleAnnotationsAttribute extends RuntimeAnnotations {

    public RuntimeInvisibleAnnotationsAttribute(int attributeNameIndex, AnnotationDesc[] annotations) {
        super(attributeNameIndex, annotations);
    }

    public static RuntimeInvisibleAnnotationsAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();
        final int numAnnotations = in.readUnsignedShort();
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
