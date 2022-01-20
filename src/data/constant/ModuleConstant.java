package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataOutputStream;
import java.io.IOException;

public class ModuleConstant implements ConstantDesc {

    private final int moduleNameIndex;

    public ModuleConstant(int ref) {
        this.moduleNameIndex = ref;
    }

    @Override
    public int getTag() {
        return 19;
    }

    public int getModuleNameIndex() {
        return this.moduleNameIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstandDesc(this.moduleNameIndex) instanceof UTF8Constant;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.moduleNameIndex);
    }
}
