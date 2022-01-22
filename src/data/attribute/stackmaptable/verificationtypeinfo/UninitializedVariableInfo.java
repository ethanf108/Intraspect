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

    public int getOffset() {
        return this.offset;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        out.writeShort(this.offset);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
