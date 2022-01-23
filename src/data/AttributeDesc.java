package data;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Describes an attribute of a class.
 */
public interface AttributeDesc {

    int getAttributeNameIndex();

    int getDataLength();

    void write(final DataOutputStream out) throws IOException;
}
