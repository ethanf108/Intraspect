package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class ClassConstant implements ConstantDesc {

    private final int utf8Index;

    public ClassConstant(int utf8) {
        this.utf8Index = utf8;
    }

    public int getUTF8Index() {
        return this.utf8Index;
    }

    @Override
    public int getTag() {
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
