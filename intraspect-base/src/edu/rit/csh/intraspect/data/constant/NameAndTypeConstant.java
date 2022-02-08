package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for NameAndType constants.
 */
public class NameAndTypeConstant implements ConstantDesc {

    private final int nameIndex;
    private final int descriptorIndex;

    public NameAndTypeConstant(final int name, final int descriptor) {
        this.nameIndex = name;
        this.descriptorIndex = descriptor;
    }

    @Override
    public int getTag() {
        return 12;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.nameIndex) instanceof UTF8Constant && ref.getConstantDesc(this.descriptorIndex) instanceof UTF8Constant;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.nameIndex);
        out.writeShort(this.descriptorIndex);
    }

}
