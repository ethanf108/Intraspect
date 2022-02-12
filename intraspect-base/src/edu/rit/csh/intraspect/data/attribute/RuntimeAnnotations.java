package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.attribute.annotation.AnnotationDesc;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This class acts as a superclass for all runtime annotations.
 */
public sealed abstract class RuntimeAnnotations implements AttributeDesc permits RuntimeVisibleAnnotationsAttribute, RuntimeInvisibleAnnotationsAttribute {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    private final AnnotationDesc[] annotations;

    protected RuntimeAnnotations(final int attributeNameIndex, final AnnotationDesc[] annotations) {
        this.attributeNameIndex = attributeNameIndex;
        this.annotations = annotations;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public AnnotationDesc[] getAnnotations() {
        return this.annotations;
    }

    public abstract boolean isRuntimeVisible();

    @Override
    public int getDataLength() {
        int length = 2;
        for (final AnnotationDesc annotation : this.annotations) {
            length += annotation.getDataLength();
        }
        return length;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.annotations.length);

        for (final AnnotationDesc annotation : this.annotations) {
            annotation.write(out);
        }
    }
}
