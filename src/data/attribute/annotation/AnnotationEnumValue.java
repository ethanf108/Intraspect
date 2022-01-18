package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class AnnotationEnumValue extends ElementValue {

    private int typeNameIndex;
    private int constNameIndex;

    public AnnotationEnumValue(int tag) {
        super(tag);
    }

    public int getTypeNameIndex() {
        return typeNameIndex;
    }

    public void setTypeNameIndex(int typeNameIndex) {
        this.typeNameIndex = typeNameIndex;
    }

    public int getConstNameIndex() {
        return constNameIndex;
    }

    public void setConstNameIndex(int constNameIndex) {
        this.constNameIndex = constNameIndex;
    }

    @Override
    ElementValue readInternal(DataInputStream in) throws IOException {
        this.typeNameIndex = in.readUnsignedShort();
        this.constNameIndex = in.readUnsignedShort();
        return this;
    }

    @Override
    public int getDataLength() {
        return 4;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.typeNameIndex);
        out.writeShort(this.constNameIndex);
    }

}
