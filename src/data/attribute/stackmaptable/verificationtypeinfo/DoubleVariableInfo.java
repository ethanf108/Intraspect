package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class DoubleVariableInfo extends VerificationTypeInfo {

    public DoubleVariableInfo(final int tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(final DataInputStream in) throws IOException {
        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }
}
