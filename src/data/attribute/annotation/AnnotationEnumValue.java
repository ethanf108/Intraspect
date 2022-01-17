package data.attribute.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.readShort;
import static util.Util.writeShort;

public final class AnnotationEnumValue extends ElementValue {

    private short typeNameIndex;
    private short constNameIndex;

    public AnnotationEnumValue(byte tag) {
        super(tag);
    }

    public short getTypeNameIndex() {
        return typeNameIndex;
    }

    public void setTypeNameIndex(short typeNameIndex) {
        this.typeNameIndex = typeNameIndex;
    }

    public short getConstNameIndex() {
        return constNameIndex;
    }

    public void setConstNameIndex(short constNameIndex) {
        this.constNameIndex = constNameIndex;
    }

    @Override
    ElementValue readInternal(InputStream in) throws IOException {
        this.typeNameIndex = readShort(in);
        this.constNameIndex = readShort(in);
        return this;
    }

    @Override
    public int getDataLength() {
        return 4;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.typeNameIndex);
        writeShort(out, this.constNameIndex);
    }

}