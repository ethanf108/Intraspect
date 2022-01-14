package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.InputStream;

public class FloatConstant implements ConstantDesc {

    private final float value;

    private FloatConstant(float val) {
        this.value = val;
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
}
