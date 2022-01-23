package data.attribute;

import data.AttributeName;
import data.attribute.annotation.AnnotationDesc;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * The RuntimeInvisibleAnnotations attribute.
 */
@AttributeName("RuntimeInvisibleAnnotations")
public final class RuntimeInvisibleAnnotationsAttribute extends RuntimeAnnotations {

    public RuntimeInvisibleAnnotationsAttribute(final int attributeNameIndex, final AnnotationDesc[] annotations) {
        super(attributeNameIndex, annotations);
    }

    public static RuntimeInvisibleAnnotationsAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final AnnotationDesc[] annotations = new AnnotationDesc[in.readUnsignedShort()];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = AnnotationDesc.read(in);
        }

        return new RuntimeInvisibleAnnotationsAttribute(ani, annotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return false;
    }
}
