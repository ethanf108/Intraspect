package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.attribute.annotation.ElementValue;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The annotation default attribute.
 */
@AttributeName("AnnotationDefault")
public final class AnnotationDefaultAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;
    private final ElementValue defaultValue;

    private AnnotationDefaultAttribute(final int attributeNameIndex, final ElementValue defaultValue) {
        this.attributeNameIndex = attributeNameIndex;
        this.defaultValue = defaultValue;
    }

    public static AnnotationDefaultAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Ignore
        return new AnnotationDefaultAttribute(ani, ElementValue.read(in));
    }

    public ElementValue getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 2 + this.defaultValue.getDataLength();
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        this.defaultValue.write(out);
    }
}
