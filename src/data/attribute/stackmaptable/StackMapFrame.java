package data.attribute.stackmaptable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Acts as a superclass for all stack map frames.
 */
public abstract sealed class StackMapFrame permits SameFrame, SameLocals1StackItemFrame, SameLocals1StackItemFrameExtended, ChopFrame, SameFrameExtended, AppendFrame, FullFrame {

    protected final int tag;

    public StackMapFrame(final int tag) {
        this.tag = tag;
    }

    public static StackMapFrame read(final DataInputStream in) throws IOException {
        final int tag = in.readUnsignedByte();  // Made this an int on purpose

        if (tag >= 0 && tag <= 63) {
            return new SameFrame(tag).readInternal(in);
        }

        if (tag >= 64 && tag <= 127) {
            return new SameLocals1StackItemFrame(tag).readInternal(in);
        }

        if (tag == 247) {
            return new SameLocals1StackItemFrameExtended(tag).readInternal(in);
        }

        if (tag >= 248 && tag <= 250) {
            return new ChopFrame(tag).readInternal(in);
        }

        if (tag == 251) {
            return new SameFrameExtended(tag).readInternal(in);
        }

        if (tag >= 252 && tag <= 254) {
            return new AppendFrame(tag).readInternal(in);
        }

        if (tag == 255) {
            return new FullFrame(tag).readInternal(in);
        }

        throw new IllegalArgumentException("Invalid tag for Stack Map Frame");
    }

    public int getTag() {
        return this.tag;
    }

    abstract StackMapFrame readInternal(final DataInputStream in) throws IOException;

    public abstract void write(final DataOutputStream out) throws IOException;

    public abstract int getDataLength();
}
