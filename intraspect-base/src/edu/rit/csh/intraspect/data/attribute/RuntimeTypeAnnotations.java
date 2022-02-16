package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.attribute.annotation.type.TypeAnnotation;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Acts as a super class for all runtime type annotations.
 */
public sealed abstract class RuntimeTypeAnnotations implements AttributeDesc permits RuntimeVisibleTypeAnnotationsAttribute, RuntimeInvisibleTypeAnnotationsAttribute {

    @ConstantPoolIndex(UTF8Constant.class)
    protected final int attributeNameIndex;

    protected final TypeAnnotation[] annotations;

    protected RuntimeTypeAnnotations(final int attributeNameIndex, final TypeAnnotation[] annotations) {
        this.attributeNameIndex = attributeNameIndex;
        this.annotations = annotations;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public TypeAnnotation[] getAnnotations() {
        return this.annotations;
    }

    public abstract boolean isRuntimeVisible();

    @Override
    public int getDataLength() {
        int length = 2;
        for (final TypeAnnotation annotation : this.annotations) {
            length += annotation.getDataLength();
        }
        return length;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.annotations.length);

        for (final TypeAnnotation annotation : this.annotations) {
            annotation.write(out);
        }
    }
}
