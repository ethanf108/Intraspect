package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class ClassConstant implements ConstantDesc {

    private final short utf8Index;

    public ClassConstant(short utf8) {
        this.utf8Index = utf8;
    }

    public short getUTF8Index() {
        return this.utf8Index;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.utf8Index) instanceof UTF8Constant;
    }
}
