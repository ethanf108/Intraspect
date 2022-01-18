package data.attribute;

import data.AttributeDesc;
import data.attribute.annotation.AnnotationDesc;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract sealed class RuntimeParameterAnnotations implements AttributeDesc permits RuntimeVisibleParameterAnnotationsAttribute, RuntimeInvisibleParameterAnnotationsAttribute {

    public static record ParameterAnnotations(AnnotationDesc[] annotations) {

        public static ParameterAnnotations read(DataInputStream in) throws IOException {
            final int numAnnotations = in.readUnsignedShort();
            AnnotationDesc[] annotations = new AnnotationDesc[numAnnotations];
            for (int i = 0; i < numAnnotations; i++) {
                annotations[i] = AnnotationDesc.read(in);
            }
            return new ParameterAnnotations(annotations);
        }

        public void write(DataOutputStream out) throws IOException {
            out.writeShort(this.annotations.length);
            for (AnnotationDesc annotation : this.annotations) {
                annotation.write(out);
            }
        }
    }

    private final int attributeNameIndex;
    private final ParameterAnnotations[] parameterAnnotations;

    RuntimeParameterAnnotations(int attributeNameIndex, ParameterAnnotations[] parameterAnnotations) {
        this.attributeNameIndex = attributeNameIndex;
        this.parameterAnnotations = parameterAnnotations;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public ParameterAnnotations[] getParameters() {
        return parameterAnnotations;
    }

    public abstract boolean isRuntimeVisible();

    @Override
    public int getDataLength() {
        int length = 1;
        for (ParameterAnnotations parameter : this.parameterAnnotations) {
            length += 2;
            for (AnnotationDesc annotation : parameter.annotations) {
                length += annotation.getDataLength();
            }
        }
        return length;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeInt(this.getDataLength());
        out.writeByte(this.parameterAnnotations.length);
        for (ParameterAnnotations parameter : this.parameterAnnotations) {
            parameter.write(out);
        }
    }
}
