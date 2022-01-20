package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;

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
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.utf8Index);
    }
}
