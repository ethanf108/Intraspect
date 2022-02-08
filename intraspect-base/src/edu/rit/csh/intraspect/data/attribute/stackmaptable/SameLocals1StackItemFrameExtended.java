package edu.rit.csh.intraspect.data.attribute.stackmaptable;

import edu.rit.csh.intraspect.data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The SameLocals1StackItemFrameExtended stack map frame type.
 */
public final class SameLocals1StackItemFrameExtended extends StackMapFrame {

    private int offsetDelta;
    private VerificationTypeInfo stack;

    public SameLocals1StackItemFrameExtended(final int tag) {
        super(tag);
    }

    public int getOffsetDelta() {
        return this.offsetDelta;
    }

    public VerificationTypeInfo getStack() {
        return this.stack;
    }

    @Override
    StackMapFrame readInternal(final DataInputStream in) throws IOException {

        this.offsetDelta = in.readUnsignedShort();

        this.stack = VerificationTypeInfo.read(in);

        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        out.writeShort(this.offsetDelta);
        this.stack.write(out);
    }

    @Override
    public int getDataLength() {
        return 3 + this.stack.getDataLength();
    }
}
