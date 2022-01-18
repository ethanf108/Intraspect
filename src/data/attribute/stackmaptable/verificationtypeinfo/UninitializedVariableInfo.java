package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class UninitializedVariableInfo extends VerificationTypeInfo {

    private int offset;

    public UninitializedVariableInfo(final int tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(final DataInputStream in) throws IOException {

        this.offset = in.readUnsignedShort();

        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(tag);
        out.writeShort(offset);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
