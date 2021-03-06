package edu.rit.csh.intraspect.data.attribute.annotation;

import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;
import edu.rit.csh.intraspect.edit.ConstantPoolIndexedRecord;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract sealed class ElementValue permits AnnotationConstantValue, AnnotationEnumValue, AnnotationClassValue, AnnotationAnnotationValue, AnnotationArrayValue {

    protected int tag;

    public ElementValue(final int tag) {
        this.tag = tag;
    }

    public static ElementValue read(final DataInputStream in) throws IOException {
        final int tag = in.read();
        return switch (tag) {
            case 'B', 'C', 'D', 'F', 'I', 'J', 'S', 'Z', 's' -> new AnnotationConstantValue(tag).readInternal(in);
            case 'e' -> new AnnotationEnumValue(tag).readInternal(in);
            case 'c' -> new AnnotationClassValue(tag).readInternal(in);
            case '@' -> new AnnotationAnnotationValue(tag).readInternal(in);
            case '[' -> new AnnotationArrayValue(tag).readInternal(in);
            default -> throw new IllegalArgumentException("Invalid tag for Element Value: " + tag);
        };
    }

    public int getTag() {
        return this.tag;
    }

    abstract ElementValue readInternal(final DataInputStream in) throws IOException;

    public abstract void write(final DataOutputStream out) throws IOException;

    public abstract int getDataLength();

    public record ElementValuePair(
            @ConstantPoolIndex(UTF8Constant.class) int elementNameIndex,
            ElementValue elementValue
    ) implements ConstantPoolIndexedRecord<ElementValuePair> {

        public static ElementValuePair read(final DataInputStream in) throws IOException {
            return new ElementValuePair(in.readUnsignedShort(), ElementValue.read(in));
        }

        public int getDataLength() {
            return 2 + this.elementValue.getDataLength();
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.elementNameIndex);
            this.elementValue.write(out);
        }

        @Override
        public ElementValuePair shift(int index, int delta) {
            return new ElementValuePair(
                    this.elementNameIndex >= index ? this.elementNameIndex + delta : this.elementNameIndex,
                    this.elementValue
            );
        }
    }

}
