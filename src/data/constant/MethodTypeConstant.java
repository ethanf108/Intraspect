package data.constant;

import data.ClassFile;
import data.ClassFiles;
import data.ConstantDesc;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for MethodType constants.
 */
public class MethodTypeConstant implements ConstantDesc {

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
}
