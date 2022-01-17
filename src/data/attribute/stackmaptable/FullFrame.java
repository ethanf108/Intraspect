package data.attribute.stackmaptable;

import data.attribute.stackmaptable.verificationtypeinfo.VerificationTypeInfo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static util.Util.*;

public final class FullFrame extends StackMapFrame {

    private short offsetDelta;
    private VerificationTypeInfo[] locals;
    private VerificationTypeInfo[] stack;

    public FullFrame(byte tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(final InputStream in) throws IOException {

        offsetDelta = readShort(in);

        final short numberOfLocals = readShort(in);
        locals = new VerificationTypeInfo[numberOfLocals];
        for (int i = 0; i < locals.length; i++) {
            locals[i] = VerificationTypeInfo.read(in);
        }

        final short numberOfStackItems = readShort(in);
        stack = new VerificationTypeInfo[numberOfLocals];
        for (int i = 0; i < stack.length; i++) {
            stack[i] = VerificationTypeInfo.read(in);
        }

        return this;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        out.write(tag);

        writeShort(out, offsetDelta);

        writeShort(out, (short) locals.length);

        for (VerificationTypeInfo local : locals) {
            local.write(out);
        }

        writeShort(out, (short) stack.length);

        for (VerificationTypeInfo verificationTypeInfo : stack) {
            verificationTypeInfo.write(out);
        }
    }

    @Override
    public int getDataLength() {
        int dataLength = 7;

        for (VerificationTypeInfo local : locals) {
            dataLength += local.getDataLength();
        }

        for (VerificationTypeInfo verificationTypeInfo : stack) {
            dataLength += verificationTypeInfo.getDataLength();
        }

        return dataLength;
    }
}
