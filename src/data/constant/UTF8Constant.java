package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UTF8Constant implements ConstantDesc {

    private final String value;

    private UTF8Constant(String val) {
        this.value = val;
    }

    @Override
    public int getTag() {
        return 1;
    }

    public String getValue() {
        return this.value;
    }

    public static UTF8Constant read(DataInputStream in) throws IOException {
        final int length = in.readUnsignedShort();
        final String val = new String(in.readNBytes(length));
        return new UTF8Constant(val);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        for (byte b : this.value.getBytes()) {
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
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getTag());
        out.writeShort(this.value.getBytes().length);
        out.write(this.value.getBytes());
    }
}
