package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class StringConstant implements ConstantDesc {

    private final int utf8Index;

    public StringConstant(int ref) {
        this.utf8Index = ref;
    }

    @Override
    public int getTag() {
        return 8;
    }

    public int getUTF8Index() {
        return this.utf8Index;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.utf8Index) instanceof UTF8Constant;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, this.utf8Index);
    }
}
