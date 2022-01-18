package data.attribute.stackmaptable;

import data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class SameLocals1StackItemFrameExtended extends StackMapFrame {

    private int offsetDelta;
    private VerificationTypeInfo stack;

    public SameLocals1StackItemFrameExtended(int tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {

        offsetDelta = in.readUnsignedShort();

        stack = VerificationTypeInfo.read(in);

        return this;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(tag);
        out.writeShort(offsetDelta);
        stack.write(out);
    }

    @Override
    public int getDataLength() {
        return 3 + stack.getDataLength();
    }
}
