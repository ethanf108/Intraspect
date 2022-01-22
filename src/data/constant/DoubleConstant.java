package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DoubleConstant implements ConstantDesc {

    private final double value;

    private DoubleConstant(final double val) {
        this.value = val;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public int getTag() {
        return 6;
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
        out.writeLong(Double.doubleToRawLongBits(this.value));
    }

    public static DoubleConstant read(final DataInputStream in) throws IOException {
        return new DoubleConstant(Double.longBitsToDouble(LongConstant.read(in).getValue()));
    }
}
