package data;

import java.io.IOException;
import java.io.OutputStream;

public interface ConstantDesc {

    byte getTag();

    boolean isValid(ClassFile ref);

    void write(OutputStream out) throws IOException;
}
