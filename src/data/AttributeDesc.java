package data;

import data.constant.UTF8Constant;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Optional;

/**
 * Describes an attribute of a class.
 */
public interface AttributeDesc {

    int getAttributeNameIndex();

    int getDataLength();

    void write(final DataOutputStream out) throws IOException;

    default Optional<String> getAttributeName(final ClassFile ref) {
        return Optional.ofNullable(ref.getConstantDesc(this.getAttributeNameIndex()) instanceof UTF8Constant u ? u.getValue() : null);
    }
}
