package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class LongVariableInfo extends VerificationTypeInfo {

    public LongVariableInfo(int tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(DataInputStream in) throws IOException {
        return this;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }
}
