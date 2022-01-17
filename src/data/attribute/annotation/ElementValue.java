package data.attribute.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.writeShort;

public abstract sealed class ElementValue permits AnnotationConstantValue, AnnotationEnumValue, AnnotationClassValue, AnnotationAnnotationValue, AnnotationArrayValue {

    public static record ElementValuePair(short elementNameIndex, ElementValue elementValue) {

        public int getDataLength() {
            return 2 + this.elementValue.getDataLength();
        }

        public void write(OutputStream out) throws IOException {
            writeShort(out, this.elementNameIndex);
            this.elementValue.write(out);
        }
    }

    protected byte tag;

    public ElementValue(byte tag) {
        this.tag = tag;
    }

    public byte getTag() {
        return tag;
    }

    abstract ElementValue readInternal(InputStream in) throws IOException;

    public abstract void write(OutputStream out) throws IOException;

    public abstract int getDataLength();

    public static ElementValue read(InputStream in) throws IOException {
        final byte tag = (byte) in.read();
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