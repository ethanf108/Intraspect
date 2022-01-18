package data.attribute.stackmaptable;

import data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;

public final class SameLocals1StackItemFrame extends StackMapFrame {

    private VerificationTypeInfo stack;

    public SameLocals1StackItemFrame(int tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {
        stack = VerificationTypeInfo.read(in);
        return this;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.write(tag);
        stack.write(out);
    }

    @Override
    public int getDataLength() {
        return 1 + stack.getDataLength();
    }
}
