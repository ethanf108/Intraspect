package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public final class TopVariableInfo extends VerificationTypeInfo {

    public TopVariableInfo(int tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(final DataInputStream in) throws IOException {
        return this;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.write(tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }
}
