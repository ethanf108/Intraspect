package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeInt;

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
    public void write(OutputStream out) throws IOException {
        out.write(getTag());
        writeInt(out, this.value);
    }
}
