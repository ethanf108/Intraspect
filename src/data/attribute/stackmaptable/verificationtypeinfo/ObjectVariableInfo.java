package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

public final class ObjectVariableInfo extends VerificationTypeInfo {

    private short cpoolIndex;

    public short getCpoolIndex() {
        return this.cpoolIndex;
    }

    public ObjectVariableInfo(byte tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(DataInputStream in) throws IOException {

        this.cpoolIndex = readShort(in);

        return this;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(tag);

    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
