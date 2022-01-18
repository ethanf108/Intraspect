package data.attribute.stackmaptable;

import data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

public final class SameLocals1StackItemFrameExtended extends StackMapFrame {

    private short offsetDelta;
    private VerificationTypeInfo stack;

    public SameLocals1StackItemFrameExtended(byte tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {

        offsetDelta = readShort(in);

        stack = VerificationTypeInfo.read(in);

        return this;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        out.write(tag);
        writeShort(out, offsetDelta);
        stack.write(out);
    }

    @Override
    public int getDataLength() {
        return 3 + stack.getDataLength();
    }
}
