package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantValue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for Double constants.
 */
public class DoubleConstant implements ConstantDesc {

    @ConstantValue(double.class)
    private final double value;

    private DoubleConstant(final double val) {
        this.value = val;
    }

    public static DoubleConstant read(final DataInputStream in) throws IOException {
        return new DoubleConstant(Double.longBitsToDouble(LongConstant.read(in).getValue()));
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
        out.writeByte(this.getTag());
        out.writeLong(Double.doubleToRawLongBits(this.value));
    }
}
