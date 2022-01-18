package data;

import java.io.IOException;
import java.io.DataOutputStream;

public interface ConstantDesc {

    int getTag();

    boolean isValid(ClassFile ref);

    void write(DataOutputStream out) throws IOException;
}
