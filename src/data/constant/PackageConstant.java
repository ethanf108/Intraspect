package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class PackageConstant implements ConstantDesc {

    private final short utf8Index;

    public PackageConstant(short ref) {
        this.utf8Index = ref;
    }

    @Override
    public byte getTag() {
        return 20;
    }

    public short getPackageNameIndex() {
        return this.utf8Index;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.utf8Index) instanceof UTF8Constant; //TODO check if valid package name?
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, this.utf8Index);
    }
}
