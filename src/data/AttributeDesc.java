package data;

import java.io.IOException;
import java.io.OutputStream;

public interface AttributeDesc {

    int getAttributeNameIndex();

    int getDataLength();

    void write(OutputStream out) throws IOException;
}
