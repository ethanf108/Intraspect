package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class AnnotationEnumValue extends ElementValue {

    private int typeNameIndex;
    private int constNameIndex;

    public AnnotationEnumValue(final int tag) {
        super(tag);
    }

    public int getTypeNameIndex() {
        return this.typeNameIndex;
    }

    public void setTypeNameIndex(final int typeNameIndex) {
        this.typeNameIndex = typeNameIndex;
    }

    public int getConstNameIndex() {
        return this.constNameIndex;
    }

    public void setConstNameIndex(final int constNameIndex) {
        this.constNameIndex = constNameIndex;
    }

    @Override
    ElementValue readInternal(final DataInputStream in) throws IOException {
        this.typeNameIndex = in.readUnsignedShort();
        this.constNameIndex = in.readUnsignedShort();
        return this;
    }

    @Override
    public int getDataLength() {
        return 5;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        out.writeShort(this.typeNameIndex);
        out.writeShort(this.constNameIndex);
    }

}
