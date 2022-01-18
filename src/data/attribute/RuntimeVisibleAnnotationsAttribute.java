package data.attribute;

import data.AttributeName;
import data.attribute.annotation.AnnotationDesc;
import java.io.IOException;
import java.io.DataInputStream;
import static util.Util.readInt;
import static util.Util.readShort;

@AttributeName("RuntimeVisibleAnnotations")
public final class RuntimeVisibleAnnotationsAttribute extends RuntimeAnnotations {

    public RuntimeVisibleAnnotationsAttribute(short attributeNameIndex, AnnotationDesc[] annotations) {
        super(attributeNameIndex, annotations);
    }

    public static RuntimeVisibleAnnotationsAttribute read(short ani, DataInputStream in) throws IOException {
        final int length = readInt(in);
        final short numAnnotations = in.readUnsignedShort();
        final AnnotationDesc[] annotations = new AnnotationDesc[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = AnnotationDesc.read(in);
        }
        return new RuntimeVisibleAnnotationsAttribute(ani, annotations);
    }

    @Override
    public boolean isRuntimeVisible() {
        return true;
    }
}
