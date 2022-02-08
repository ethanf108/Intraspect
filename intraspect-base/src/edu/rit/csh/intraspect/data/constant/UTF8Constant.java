package edu.rit.csh.intraspect.data.constant;

import edu.rit.csh.intraspect.data.ClassFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * A ConstantDescriptor for UTF8 constants.
 */
public class UTF8Constant implements ConstantDesc {

    private final String value;

    private UTF8Constant(final String val) {
        this.value = val;
    }

    public static UTF8Constant read(final DataInputStream in) throws IOException {
        return new UTF8Constant(new String(in.readNBytes(in.readUnsignedShort())));
    }

    @Override
    public int getTag() {
        return 1;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        for (final byte b : this.value.getBytes()) {
            final int val = b & 0xFF;
            if (val == 0 || val > 0xF0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isWide() {
        return false;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.value.getBytes().length);
        out.write(this.value.getBytes());
    }
}
