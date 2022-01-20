package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LongConstant implements ConstantDesc {

    private final long value;

    private LongConstant(long val) {
        this.value = val;
    }

    @Override
    public int getTag() {
        return 5;
    }

    public long getValue() {
        return value;
    }

    public static LongConstant read(DataInputStream in) throws IOException {
        long val = 0;
        for (byte b : in.readNBytes(8)) {
            val <<= 8;
            val |= b;
        }
        return new LongConstant(val);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }

    @Override
    public boolean isWide() {
        return true;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(getTag());
        out.writeLong(this.value);
    }
}
