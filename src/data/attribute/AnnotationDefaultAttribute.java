package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import data.attribute.annotation.ElementValue;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

@AttributeName("AnnotationDefault")
public class AnnotationDefaultAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final ElementValue defaultValue;

    private AnnotationDefaultAttribute(final short attributeNameIndex, final ElementValue defaultValue) {
        this.attributeNameIndex = attributeNameIndex;
        this.defaultValue = defaultValue;
    }

    public static AnnotationDefaultAttribute read(final short ani, final DataInputStream in) throws IOException {
        readInt(in);    // Ignore
        return new AnnotationDefaultAttribute(ani, ElementValue.read(in));
    }

    public ElementValue getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 2 + defaultValue.getDataLength();
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, getDataLength());
        this.defaultValue.write(out);
    }
}
