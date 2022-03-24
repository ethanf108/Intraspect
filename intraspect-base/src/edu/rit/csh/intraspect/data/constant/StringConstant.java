package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for String constants.
 */
public final class StringConstant implements ConstantDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int utf8Index;

    public StringConstant(final int ref) {
        this.utf8Index = ref;
    }

    @Override
    public int getTag() {
        return 8;
    }

    public int getUTF8Index() {
        return this.utf8Index;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.utf8Index) instanceof UTF8Constant;
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

    @Override
    public String getName() {
        return "String";
    }

    @Override
    public String getInfo() {
        return "#" + this.utf8Index;
    }
}
