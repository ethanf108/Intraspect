package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class AnnotationClassValue extends ElementValue {

    private int classInfoIndex;

    public AnnotationClassValue(final int tag) {
        super(tag);
    }

    public int getClassInfoIndex() {
        return this.classInfoIndex;
    }

    public void setClassInfoIndex(final int classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    @Override
    ElementValue readInternal(final DataInputStream in) throws IOException {
        this.classInfoIndex = in.readUnsignedShort();
        return this;
    }

    @Override
    public int getDataLength() {
        return 3;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        out.writeShort(this.classInfoIndex);
    }

}
