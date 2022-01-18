package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class AnnotationConstantValue extends ElementValue {

    private int constValueIndex;

    public AnnotationConstantValue(int tag) {
        super(tag);
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public int getConstValueIndex() {
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
    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.constValueIndex);
    }

}
