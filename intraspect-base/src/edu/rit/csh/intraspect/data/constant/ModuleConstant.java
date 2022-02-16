package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for a Module constants.
 */
public final class ModuleConstant implements ConstantDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private int moduleNameIndex;

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

    @Override
    public String getName() {
        return "Module";
    }

    @Override
    public String getInfo() {
        return "#" + this.moduleNameIndex;
    }
}
