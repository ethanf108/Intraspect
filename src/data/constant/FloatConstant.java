package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static util.Util.writeInt;

public class FloatConstant implements ConstantDesc {

    private final float value;

    private FloatConstant(float val) {
        this.value = val;
    }

    @Override
    public byte getTag() {
        return 4;
    }

    public float getValue() {
        return this.value;
    }

    public static FloatConstant read(InputStream in) throws IOException {
        return new FloatConstant(Float.intBitsToFloat(IntegerConstant.read(in).getValue()));
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(getTag());
        writeInt(out, Float.floatToRawIntBits(this.value));
    }
}
