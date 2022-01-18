package data.attribute.annotation;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readShort;
import static util.Util.writeShort;

public final class AnnotationConstantValue extends ElementValue {

    private short constValueIndex;

    public AnnotationConstantValue(byte tag) {
        super(tag);
    }

    public void setTag(byte tag) {
        this.tag = tag;
    }

    public short getConstValueIndex() {
        return constValueIndex;
    }

    public void setConstValueIndex(short constValueIndex) {
        this.constValueIndex = constValueIndex;
    }

    @Override
    ElementValue readInternal(DataInputStream in) throws IOException {
        this.constValueIndex = in.readUnsignedShort();
        return this;
    }

    @Override
    public int getDataLength() {
        return 2;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.constValueIndex);
    }

}
