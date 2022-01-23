package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for long constants.
 */
public class LongConstant implements ConstantDesc {

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
        return value;
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
        out.writeByte(getTag());
        out.writeLong(this.value);
    }
}
