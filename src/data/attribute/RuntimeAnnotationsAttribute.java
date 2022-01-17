package data.attribute;

import data.AttributeDesc;
import data.attribute.RuntimeInvisibleAnnotationsAttribute;
import data.attribute.RuntimeVisibleAnnotationsAttribute;
import data.attribute.annotation.AnnotationDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeInt;
import static util.Util.writeShort;

public sealed abstract class RuntimeAnnotationsAttribute implements AttributeDesc permits RuntimeVisibleAnnotationsAttribute, RuntimeInvisibleAnnotationsAttribute {

    private final short attributeNameIndex;
    private final AnnotationDesc[] annotations;

    public RuntimeAnnotationsAttribute(short attributeNameIndex, AnnotationDesc[] annotations) {
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

    public abstract boolean isRuntimeVisible();

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
