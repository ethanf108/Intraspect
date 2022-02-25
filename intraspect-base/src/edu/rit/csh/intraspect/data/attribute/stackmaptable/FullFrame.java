package edu.rit.csh.intraspect.data.attribute.stackmaptable;

import edu.rit.csh.intraspect.data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The FullFrame stack map frame type.
 */
public final class FullFrame extends StackMapFrame {

    private int offsetDelta;
    private VerificationTypeInfo[] locals;
    private VerificationTypeInfo[] stack;

    public FullFrame(final int tag) {
        super(tag);
    }

    public FullFrame(int offsetDelta, VerificationTypeInfo[] locals, VerificationTypeInfo[] stack) {
        super(255);
        this.offsetDelta = offsetDelta;
        this.locals = locals;
        this.stack = stack;
    }

    public int getOffsetDelta() {
        return this.offsetDelta;
    }

    public VerificationTypeInfo[] getLocals() {
        return this.locals;
    }

    public VerificationTypeInfo[] getStack() {
        return this.stack;
    }

    @Override
    StackMapFrame readInternal(final DataInputStream in) throws IOException {

        this.offsetDelta = in.readUnsignedShort();

        this.locals = new VerificationTypeInfo[in.readUnsignedShort()];
        for (int i = 0; i < this.locals.length; i++) {
            this.locals[i] = VerificationTypeInfo.read(in);
        }

        this.stack = new VerificationTypeInfo[in.readUnsignedShort()];
        for (int i = 0; i < this.stack.length; i++) {
            this.stack[i] = VerificationTypeInfo.read(in);
        }

        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);

        out.writeShort(this.offsetDelta);

        out.writeShort(this.locals.length);

        for (final VerificationTypeInfo local : this.locals) {
            local.write(out);
        }

        out.writeShort(this.stack.length);

        for (final VerificationTypeInfo verificationTypeInfo : this.stack) {
            verificationTypeInfo.write(out);
        }
    }

    @Override
    public int getDataLength() {
        int dataLength = 7;

        for (final VerificationTypeInfo local : this.locals) {
            dataLength += local.getDataLength();
        }

        for (final VerificationTypeInfo verificationTypeInfo : this.stack) {
            dataLength += verificationTypeInfo.getDataLength();
        }

        return dataLength;
    }
}
