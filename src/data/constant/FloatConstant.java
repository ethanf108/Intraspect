package data.constant;

import data.ClassFile;
import data.ConstantDesc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FloatConstant implements ConstantDesc {

    private final float value;

    private FloatConstant(final float val) {
        this.value = val;
    }

    @Override
    public int getTag() {
        return 4;
    }

    public float getValue() {
        return this.value;
    }

    public static FloatConstant read(DataInputStream in) throws IOException {
        return new FloatConstant(Float.intBitsToFloat(IntegerConstant.read(in).getValue()));
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return true;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(getTag());
        out.writeInt(Float.floatToRawIntBits(this.value));
    }
}
