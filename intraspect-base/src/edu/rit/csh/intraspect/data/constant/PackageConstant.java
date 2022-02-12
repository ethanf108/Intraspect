package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for Package Constants.
 */
public final class PackageConstant implements ConstantDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private int utf8Index;

    public PackageConstant(final int ref) {
        this.utf8Index = ref;
    }

    @Override
    public int getTag() {
        return 20;
    }

    public int getPackageNameIndex() {
        return this.utf8Index;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.utf8Index) instanceof UTF8Constant; //TODO check if valid package name?
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.utf8Index);
    }
}
