package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.ConstantValue;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for Float constants.
 */
public final class FloatConstant implements ConstantDesc {

    @ConstantValue(float.class)
    private float value;

    private FloatConstant(final float val) {
        this.value = val;
    }

    public static FloatConstant read(final DataInputStream in) throws IOException {
        return new FloatConstant(Float.intBitsToFloat(IntegerConstant.read(in).getValue()));
    }

    @Override
    public int getTag() {
        return 4;
    }

    public float getValue() {
        return this.value;
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
        out.writeByte(this.getTag());
        out.writeInt(Float.floatToRawIntBits(this.value));
    }

    @Override
    public String getName() {
        return "Float";
    }

    @Override
    public String getInfo() {
        return this.value + "f";
    }
}
