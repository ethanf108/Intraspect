package data;

import java.io.IOException;
import java.io.DataOutputStream;

public interface AttributeDesc {

    int getAttributeNameIndex();

    int getDataLength();

    void write(final DataOutputStream out) throws IOException;
}
