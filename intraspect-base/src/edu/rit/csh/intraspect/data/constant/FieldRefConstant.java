package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for FieldRef constants.
 */
public final class FieldRefConstant implements ConstantDesc {

    @ConstantPoolIndex(ClassConstant.class)
    private int classIndex;

    @ConstantPoolIndex(NameAndTypeConstant.class)
    private int nameAndTypeIndex;

    public FieldRefConstant(final int classIndex, final int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public int getTag() {
        return 9;
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    public int getNameAndTypeIndex() {
        return this.nameAndTypeIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO validate field??
        return ref.getConstantDesc(this.classIndex) instanceof ClassConstant && ref.getConstantDesc(this.nameAndTypeIndex) instanceof NameAndTypeConstant;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.classIndex);
        out.writeShort(this.nameAndTypeIndex);
    }

    @Override
    public String getName() {
        return "FieldRef";
    }

    @Override
    public String getInfo() {
        return "#" + this.classIndex + ", #" + this.nameAndTypeIndex;
    }
}
