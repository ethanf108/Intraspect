package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for a Module constants.
 */
public class ModuleConstant implements ConstantDesc {

    private final int moduleNameIndex;

    public ModuleConstant(final int ref) {
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
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.moduleNameIndex) instanceof UTF8Constant;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.moduleNameIndex);
    }
}
