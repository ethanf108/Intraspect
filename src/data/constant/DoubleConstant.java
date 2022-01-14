package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.InputStream;

public class DoubleConstant implements ConstantDesc {

    private final double value;

    private DoubleConstant(double val) {
        this.value = val;
    }

    public double getValue() {
        return this.value;
    }

    public static DoubleConstant read(InputStream in) throws IOException {
        return new DoubleConstant(Double.longBitsToDouble(LongConstant.read(in).getValue()));
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }
}
