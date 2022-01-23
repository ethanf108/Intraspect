package data.attribute.stackmaptable;

import data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The AppendFrame stack map frame type.
 */
public final class AppendFrame extends StackMapFrame {

    private int offsetDelta;
    private VerificationTypeInfo[] locals;

    public AppendFrame(int tag) {
        super(tag);
    }

    public int getOffsetDelta() {
        return this.offsetDelta;
    }

    public VerificationTypeInfo[] getLocals() {
        return this.locals;
    }

    @Override
    StackMapFrame readInternal(final DataInputStream in) throws IOException {

        this.offsetDelta = in.readUnsignedShort();

        this.locals = new VerificationTypeInfo[this.tag - 251];

        for (int i = 0; i < this.locals.length; i++) {
            this.locals[i] = VerificationTypeInfo.read(in);
        }

        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(tag);
        out.writeShort(this.offsetDelta);

        for (final VerificationTypeInfo local : this.locals) {
            local.write(out);
        }

    }

    @Override
    public int getDataLength() {
        int dataLength = 3;

        for (final VerificationTypeInfo local : this.locals) {
            dataLength += local.getDataLength();
        }

        return dataLength;
    }
}
