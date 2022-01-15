package data;

import java.io.IOException;
import java.io.OutputStream;

public interface AttributeDesc {

    public short getAttributeNameIndex();

    public int getDataLength();

    public void write(OutputStream out) throws IOException;
}
