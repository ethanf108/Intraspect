package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import data.attribute.annotation.AnnotationDesc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.readShort;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("RuntimeVisibleAnnotations")
public class RuntimeVisibleAnnotationsAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final AnnotationDesc[] annotations;

    public RuntimeVisibleAnnotationsAttribute(short attributeNameIndex, AnnotationDesc[] annotations) {
        this.attributeNameIndex = attributeNameIndex;
        this.annotations = annotations;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public AnnotationDesc[] getAnnotations() {
        return annotations;
    }

    public static RuntimeVisibleAnnotationsAttribute read(short ani, InputStream in) throws IOException {
        final int length = readInt(in);
        final short numAnnotations = readShort(in);
        final AnnotationDesc[] annotations = new AnnotationDesc[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = AnnotationDesc.read(in);
        }
        return new RuntimeVisibleAnnotationsAttribute(ani, annotations);
    }

    @Override
    public int getDataLength() {
        int length = 2;
        for (AnnotationDesc annotation : this.annotations) {
            length += annotation.getDataLength();
        }
        return length;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeInt(out, this.getDataLength());
        writeShort(out, (short) this.annotations.length);
        for (AnnotationDesc annotation : annotations) {
            annotation.write(out);
        }
    }
}
