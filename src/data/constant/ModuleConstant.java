package data.constant;

import data.ClassFile;
import data.ConstantDesc;

public class ModuleConstant implements ConstantDesc {

    private final short moduleNameIndex;

    public ModuleConstant(short ref) {
        this.moduleNameIndex = ref;
    }

    public short getModuleNameIndex() {
        return this.moduleNameIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.moduleNameIndex) instanceof UTF8Constant;
    }
}
