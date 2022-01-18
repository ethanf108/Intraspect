package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public final class ObjectVariableInfo extends VerificationTypeInfo {

    public ObjectVariableInfo(int tag) {
        super(tag);
    }

    private int cpoolIndex;

    public int getCpoolIndex() {
        return this.cpoolIndex;
    }

    @Override
    VerificationTypeInfo readInternal(DataInputStream in) throws IOException {

        this.cpoolIndex = in.readUnsignedShort();

        return this;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.write(tag);

    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
