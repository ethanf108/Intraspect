package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.InputStream;

public class LongConstant implements ConstantDesc {

    private final long value;

    private LongConstant(long val) {
        this.value = val;
    }

    public long getValue() {
        return value;
    }

    public static LongConstant read(InputStream in) throws IOException {
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
}
