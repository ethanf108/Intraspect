package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for Integer constants.
 */
public class IntegerConstant implements ConstantDesc {

    private final int value;

    private IntegerConstant(final int val) {
        this.value = val;
    }

    public static IntegerConstant read(final DataInputStream in) throws IOException {
        int val = 0;
        for (byte b : in.readNBytes(4)) {
            val <<= 8;
            val |= b;
        }
        return new IntegerConstant(val);
    }

    @Override
    public int getTag() {
        return 3;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(getTag());
        out.writeInt(this.value);
    }
}
