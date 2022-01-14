package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class StringConstant implements ConstantDesc {

    private final short utf8Index;

    public StringConstant(short ref) {
        this.utf8Index = ref;
    }

    public short getUTF8Index() {
        return this.utf8Index;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.utf8Index) instanceof UTF8Constant;
    }
}
