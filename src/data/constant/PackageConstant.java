package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class PackageConstant implements ConstantDesc {

    private final int utf8Index;

    public PackageConstant(int ref) {
        this.utf8Index = ref;
    }

    @Override
    public int getTag() {
        return 20;
    }

    public int getPackageNameIndex() {
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
