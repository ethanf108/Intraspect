package data;

import java.io.DataOutputStream;
import java.io.IOException;

public interface ConstantDesc {

    int getTag();

    boolean isValid(ClassFile ref);

    boolean isWide();

    void write(DataOutputStream out) throws IOException;
}
