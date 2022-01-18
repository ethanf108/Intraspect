package data;

import java.io.IOException;
import java.io.DataOutputStream;

public interface AttributeDesc {

    int getAttributeNameIndex();

    int getDataLength();

    void write(DataOutputStream out) throws IOException;
}
