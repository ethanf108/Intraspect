package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.attribute.annotation.AnnotationDesc;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Acts as a super class for all runtime parameter annotations.
 */
public abstract sealed class RuntimeParameterAnnotations implements AttributeDesc permits RuntimeVisibleParameterAnnotationsAttribute, RuntimeInvisibleParameterAnnotationsAttribute {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    private final ParameterAnnotations[] parameterAnnotations;

    protected RuntimeParameterAnnotations(final int attributeNameIndex, final ParameterAnnotations[] parameterAnnotations) {
        this.attributeNameIndex = attributeNameIndex;
        this.parameterAnnotations = parameterAnnotations;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public ParameterAnnotations[] getParameters() {
        return this.parameterAnnotations;
    }

    public abstract boolean isRuntimeVisible();

    @Override
    public int getDataLength() {
        int length = 1;
        for (final ParameterAnnotations parameter : this.parameterAnnotations) {
            length += 2;
            for (final AnnotationDesc annotation : parameter.annotations) {
                length += annotation.getDataLength();
            }
        }
        return length;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeInt(this.getDataLength());
        out.writeByte(this.parameterAnnotations.length);
        for (final ParameterAnnotations parameter : this.parameterAnnotations) {
            parameter.write(out);
        }
    }

    public record ParameterAnnotations(AnnotationDesc[] annotations) {

        public static ParameterAnnotations read(final DataInputStream in) throws IOException {

            AnnotationDesc[] annotations = new AnnotationDesc[in.readUnsignedShort()];

            for (int i = 0; i < annotations.length; i++) {
                annotations[i] = AnnotationDesc.read(in);
            }

            return new ParameterAnnotations(annotations);
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.annotations.length);
            for (final AnnotationDesc annotation : this.annotations) {
                annotation.write(out);
            }
        }
    }
}
