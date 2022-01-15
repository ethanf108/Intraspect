package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.writeLong;

public class DoubleConstant implements ConstantDesc {

    private final double value;

    private DoubleConstant(double val) {
        this.value = val;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public byte getTag() {
        return 6;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(getTag());
        writeLong(out, Double.doubleToRawLongBits(this.value));
    }

    public static DoubleConstant read(InputStream in) throws IOException {
        return new DoubleConstant(Double.longBitsToDouble(LongConstant.read(in).getValue()));
    }
}
