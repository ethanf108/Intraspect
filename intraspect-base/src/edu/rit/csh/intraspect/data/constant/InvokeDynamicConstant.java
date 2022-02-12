package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;


public final class InvokeDynamicConstant implements ConstantDesc {

    private int bootstrapMethodAttributeIndex;

    @ConstantPoolIndex(NameAndTypeConstant.class)
    private int nameAndTypeIndex;

    public InvokeDynamicConstant(final int bootstrapMethodAttributeIndex, final int nameAndTypeIndex) {
        this.bootstrapMethodAttributeIndex = bootstrapMethodAttributeIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int getTag() {
        return 18;
    }

    public int getBootstrapMethodAttributeIndex() {
        return this.bootstrapMethodAttributeIndex;
    }

    public int getNameAndTypeIndex() {
        return this.nameAndTypeIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check bootstrap method table
        return ref.getConstantDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.bootstrapMethodAttributeIndex);
        out.writeShort(this.nameAndTypeIndex);
    }
}
