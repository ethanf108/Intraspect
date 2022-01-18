package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

public final class LongVariableInfo extends VerificationTypeInfo {
    public LongVariableInfo(byte tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(DataInputStream in) throws IOException {
        return this;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(tag);
    }

    @Override
    public int getDataLength() {
        return 1;
    }
}
