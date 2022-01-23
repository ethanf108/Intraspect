package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The UninitializedObjectInfo verification type.
 */
public final class ObjectVariableInfo extends VerificationTypeInfo {

    private int cpoolIndex;

    public ObjectVariableInfo(final int tag) {
        super(tag);
    }

    public int getCpoolIndex() {
        return this.cpoolIndex;
    }

    @Override
    VerificationTypeInfo readInternal(final DataInputStream in) throws IOException {

        this.cpoolIndex = in.readUnsignedShort();

        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        out.writeShort(this.cpoolIndex);

    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
