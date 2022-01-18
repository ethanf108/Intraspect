package data.attribute.annotation;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readShort;
import static util.Util.writeShort;

public final class AnnotationClassValue extends ElementValue {

    private short classInfoIndex;

    public AnnotationClassValue(byte tag) {
        super(tag);
    }

    public short getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(short classInfoIndex) {
        this.classInfoIndex = classInfoIndex;
    }

    @Override
    ElementValue readInternal(DataInputStream in) throws IOException {
        this.classInfoIndex = in.readUnsignedShort();
        return this;
    }

    @Override
    public int getDataLength() {
        return 2;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.classInfoIndex);
    }

}
