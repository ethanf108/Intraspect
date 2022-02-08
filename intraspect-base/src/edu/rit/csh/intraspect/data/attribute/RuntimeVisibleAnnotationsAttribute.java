package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.attribute.annotation.AnnotationDesc;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * The RuntimeVisibleAnnotations attribute.
 */
@AttributeName("RuntimeVisibleAnnotations")
public final class RuntimeVisibleAnnotationsAttribute extends RuntimeAnnotations {

    public RuntimeVisibleAnnotationsAttribute(final int attributeNameIndex, final AnnotationDesc[] annotations) {
        super(attributeNameIndex, annotations);
    }

    public static RuntimeVisibleAnnotationsAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore

        final AnnotationDesc[] annotations = new AnnotationDesc[in.readUnsignedShort()];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = AnnotationDesc.read(in);
        }

        return new RuntimeVisibleAnnotationsAttribute(ani, annotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return true;
    }
}
