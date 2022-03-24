package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.ClassFiles;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for MethodType constants.
 */
public final class MethodTypeConstant implements ConstantDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int descriptorIndex;

    public MethodTypeConstant(final int ref) {
        this.descriptorIndex = ref;
    }

    @Override
    public int getTag() {
        return 16;
    }

    public int getMethodDescriptorIndex() {
        return this.descriptorIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.descriptorIndex) instanceof UTF8Constant u && ClassFiles.isValidMethodDescriptor(u.getValue());
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.descriptorIndex);
    }

    @Override
    public String getName() {
        return "MethodType";
    }

    @Override
    public String getInfo() {
        return "#" + this.descriptorIndex;
    }
}
