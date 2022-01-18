package data.attribute.stackmaptable;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

public abstract sealed class StackMapFrame permits SameFrame, SameLocals1StackItemFrame, SameLocals1StackItemFrameExtended, ChopFrame, SameFrameExtended, AppendFrame, FullFrame {

    protected final byte tag;

    public StackMapFrame(final byte tag) {
        this.tag = tag;
    }

    public byte getTag() {
        return this.tag;
    }

    abstract StackMapFrame readInternal(DataInputStream in) throws IOException;

    public abstract void write(OutputStream out) throws IOException;

    public abstract int getDataLength();

    public static StackMapFrame read(final DataInputStream in) throws IOException {
        final int tag = in.read();  // Made this an int on purpose

        if (tag >= 0 && tag <= 63) {
            return new SameFrame((byte) tag).readInternal(in);
        }

        if (tag >= 64 && tag <= 127) {
            return new SameLocals1StackItemFrame((byte) tag).readInternal(in);
        }

        if (tag == 247) {
            return new SameLocals1StackItemFrameExtended((byte) tag).readInternal(in);
        }

        if (tag >= 248 && tag <= 250) {
            return new ChopFrame((byte) tag).readInternal(in);
        }

        if (tag == 251) {
            return new SameFrameExtended((byte) tag).readInternal(in);
        }

        if (tag >= 252 && tag <= 254) {
            return new AppendFrame((byte) tag).readInternal(in);
        }

        if (tag == 255) {
            return new FullFrame((byte) tag).readInternal(in);
        }

        throw new IllegalArgumentException("Invalid tag for Stack Map Frame");
    }
}
