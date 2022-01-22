package data.attribute.stackmaptable;

import data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class SameLocals1StackItemFrame extends StackMapFrame {

    public VerificationTypeInfo getStack() {
        return this.stack;
    }

    private VerificationTypeInfo stack;

    public SameLocals1StackItemFrame(final int tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(final DataInputStream in) throws IOException {
        this.stack = VerificationTypeInfo.read(in);
        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        this.stack.write(out);
    }

    @Override
    public int getDataLength() {
        return 1 + stack.getDataLength();
    }
}
