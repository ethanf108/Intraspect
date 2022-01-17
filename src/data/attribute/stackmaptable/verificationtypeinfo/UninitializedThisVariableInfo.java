package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class UninitializedThisVariableInfo extends VerificationTypeInfo {

    public UninitializedThisVariableInfo(byte tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(InputStream in) throws IOException {
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
