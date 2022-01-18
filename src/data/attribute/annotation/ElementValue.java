package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract sealed class ElementValue permits AnnotationConstantValue, AnnotationEnumValue, AnnotationClassValue, AnnotationAnnotationValue, AnnotationArrayValue {

    public static record ElementValuePair(int elementNameIndex, ElementValue elementValue) {

        public int getDataLength() {
            return 2 + this.elementValue.getDataLength();
        }

        public static ElementValuePair read(DataInputStream in) throws IOException {
            return new ElementValuePair(in.readUnsignedShort(), ElementValue.read(in));
        }

        public void write(DataOutputStream out) throws IOException {
            out.writeShort(this.elementNameIndex);
            this.elementValue.write(out);
        }
    }

    protected int tag;

    public ElementValue(int tag) {
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }

    abstract ElementValue readInternal(DataInputStream in) throws IOException;

    public abstract void write(DataOutputStream out) throws IOException;

    public abstract int getDataLength();

    public static ElementValue read(DataInputStream in) throws IOException {
        final int tag = in.read();
        return switch (tag) {
            case 'B','C','D','F','I','J','S','Z','s' ->
                new AnnotationConstantValue(tag).readInternal(in);
            case 'e' ->
                new AnnotationEnumValue(tag).readInternal(in);
            case 'c' ->
                new AnnotationClassValue(tag).readInternal(in);
            case '@' ->
                new AnnotationAnnotationValue(tag).readInternal(in);
            case '[' ->
                new AnnotationArrayValue(tag).readInternal(in);
            default ->
                throw new IllegalArgumentException("Invalid tag for Element Value");
        };
    }

}
