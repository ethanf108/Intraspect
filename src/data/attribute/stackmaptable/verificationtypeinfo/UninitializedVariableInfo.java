package data.attribute.stackmaptable.verificationtypeinfo;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.readShort;
import static util.Util.writeShort;

public final class UninitializedVariableInfo extends VerificationTypeInfo {

    private short offset;

    public UninitializedVariableInfo(final byte tag) {
        super(tag);
    }

    @Override
    VerificationTypeInfo readInternal(final DataInputStream in) throws IOException {

        this.offset = readShort(in);

        return this;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        out.write(tag);
        writeShort(out, offset);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
