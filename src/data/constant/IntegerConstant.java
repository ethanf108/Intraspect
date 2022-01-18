package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IntegerConstant implements ConstantDesc {

    private final int value;

    private IntegerConstant(int val) {
        this.value = val;
    }

    @Override
    public int getTag() {
        return 3;
    }

    public int getValue() {
        return this.value;
    }

    public static IntegerConstant read(DataInputStream in) throws IOException {
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

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(getTag());
        out.writeInt(this.value);
    }
}
