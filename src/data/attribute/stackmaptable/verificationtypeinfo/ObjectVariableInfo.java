package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class ObjectVariableInfo extends VerificationTypeInfo {

    public ObjectVariableInfo(final int tag) {
        super(tag);
    }

    private int cpoolIndex;

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
        out.writeByte(tag);
        out.writeShort(this.cpoolIndex);

    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
