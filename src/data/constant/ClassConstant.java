package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.IOException;
import java.io.OutputStream;

import static util.Util.writeShort;

public class ClassConstant implements ConstantDesc {

    private final short utf8Index;

    public ClassConstant(short utf8) {
        this.utf8Index = utf8;
    }

    public short getUTF8Index() {
        return this.utf8Index;
    }

    @Override
    public byte getTag() {
        return 7;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.utf8Index) instanceof UTF8Constant;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(getTag());
        writeShort(out, this.utf8Index);
    }
}
