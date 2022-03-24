package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantValue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for long constants.
 */
public final class LongConstant implements ConstantDesc {

    @ConstantValue(long.class)
    private long value;

    private LongConstant(final long val) {
        this.value = val;
    }

    public static LongConstant read(final DataInputStream in) throws IOException {
        return new LongConstant(in.readLong());
    }

    @Override
    public int getTag() {
        return 5;
    }

    public long getValue() {
        return this.value;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return true;
    }

    @Override
    public boolean isWide() {
        return true;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeLong(this.value);
    }

    @Override
    public String getName() {
        return "Long";
    }

    @Override
    public String getInfo() {
        return this.value + "L";
    }
}
