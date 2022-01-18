package data.attribute.annotation;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public final class AnnotationClassValue extends ElementValue {

    private int classInfoIndex;

    public AnnotationClassValue(int tag) {
        super(tag);
    }

    public int getClassInfoIndex() {
        return classInfoIndex;
    }

    public void setClassInfoIndex(int classInfoIndex) {
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
