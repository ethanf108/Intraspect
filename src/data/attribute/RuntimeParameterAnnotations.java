package data.attribute;

import data.AttributeDesc;
import data.attribute.annotation.AnnotationDesc;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readShort;
import static util.Util.writeInt;
import static util.Util.writeShort;

public abstract sealed class RuntimeParameterAnnotations implements AttributeDesc permits RuntimeVisibleParameterAnnotationsAttribute, RuntimeInvisibleParameterAnnotationsAttribute {

    public static record ParameterAnnotations(AnnotationDesc[] annotations) {

        public static ParameterAnnotations read(DataInputStream in) throws IOException {
            final short numAnnotations = in.readUnsignedShort();
            AnnotationDesc[] annotations = new AnnotationDesc[numAnnotations];
            for (int i = 0; i < numAnnotations; i++) {
                annotations[i] = AnnotationDesc.read(in);
            }
            return new ParameterAnnotations(annotations);
        }

        public void write(OutputStream out) throws IOException {
            writeShort(out, (short) this.annotations.length);
            for (AnnotationDesc annotation : this.annotations) {
                annotation.write(out);
            }
        }
    }

    private final short attributeNameIndex;
    private final ParameterAnnotations[] parameterAnnotations;

    RuntimeParameterAnnotations(short attributeNameIndex, ParameterAnnotations[] parameterAnnotations) {
        this.attributeNameIndex = attributeNameIndex;
        this.parameterAnnotations = parameterAnnotations;
    }

    @Override
    public short getAttributeNameIndex() {
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
    public void write(OutputStream out) throws IOException {
        writeInt(out, this.getDataLength());
        out.write((byte) this.parameterAnnotations.length);
        for (ParameterAnnotations parameter : this.parameterAnnotations) {
            parameter.write(out);
        }
    }
}
