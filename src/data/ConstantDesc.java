package data;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ConstantDesc {

    int getTag();

    boolean isValid(final ClassFile ref);

    boolean isWide();

    void write(final DataOutputStream out) throws IOException;
}
