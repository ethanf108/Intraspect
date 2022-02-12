package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for long constants.
 */
public final class LongConstant implements ConstantDesc {

    private final long value;

    private LongConstant(final long val) {
        this.value = val;
    }

    public static LongConstant read(final DataInputStream in) throws IOException {
        long val = 0;
        for (final byte b : in.readNBytes(8)) {
            val <<= 8;
            val |= b;
        }
        return new LongConstant(val);
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
}
