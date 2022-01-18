package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class FloatVariableInfo extends VerificationTypeInfo {

    public FloatVariableInfo(int tag) {
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
