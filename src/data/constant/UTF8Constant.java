package data.constant;

import data.ClassFile;
import data.ConstantDesc;
import java.io.IOException;
import java.io.InputStream;

public class UTF8Constant implements ConstantDesc {

    private final String value;

    private UTF8Constant(String val) {
        this.value = val;
    }

    public String getValue() {
        return this.value;
    }

    public static UTF8Constant read(InputStream in) throws IOException {
        final short length = ClassFile.readShort(in);
        final String val = new String(in.readNBytes(length));
        return new UTF8Constant(val);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return this.value != null;
    }
}
