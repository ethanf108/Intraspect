package data;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 * An interface representing a constant descriptor.
 */
public interface ConstantDesc {

    int getTag();

    boolean isValid(final ClassFile ref);

    boolean isWide();

    /**
     * Writes the constant descriptor to the given output stream.
     *
     * @param out the output stream to write to
     * @throws IOException if an I/O error occurs
     */
    void write(DataOutputStream out) throws IOException;
}
