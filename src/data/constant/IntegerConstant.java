package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.InputStream;

public class IntegerConstant implements ConstantDesc {

    private final int value;

    private IntegerConstant(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

    public static IntegerConstant read(InputStream in) throws IOException {
        int val = 0;
        for (byte b : in.readNBytes(4)) {
            val <<= 8;
            val |= b;
        }
        return new IntegerConstant(val);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }
}
