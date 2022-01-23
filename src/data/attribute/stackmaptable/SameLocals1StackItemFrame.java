package data.attribute.stackmaptable;

import data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The SameLocals1StackItemFrame stack map frame type.
 */
public final class SameLocals1StackItemFrame extends StackMapFrame {

    private VerificationTypeInfo stack;

    public SameLocals1StackItemFrame(final int tag) {
        super(tag);
    }

    public VerificationTypeInfo getStack() {
        return this.stack;
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
