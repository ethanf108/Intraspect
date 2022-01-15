package data;

import java.io.IOException;
import java.io.OutputStream;

public interface ConstantDesc {

    public byte getTag();

    public boolean isValid(ClassFile ref);

    public void write(OutputStream out) throws IOException;
}
