package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.readShort;
import static util.Util.writeShort;

public class UTF8Constant implements ConstantDesc {

    private final String value;

    private UTF8Constant(String val) {
        this.value = val;
    }

    @Override
    public byte getTag() {
        return 1;
    }

    public String getValue() {
        return this.value;
    }

    public static UTF8Constant read(InputStream in) throws IOException {
        final short length = readShort(in);
        final String val = new String(in.readNBytes(length));
        return new UTF8Constant(val);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return this.value != null;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(this.getTag());
        writeShort(out, (short) this.value.getBytes().length);
        out.write(this.value.getBytes());
    }
}
