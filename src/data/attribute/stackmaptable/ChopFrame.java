package data.attribute.stackmaptable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The ChopFrame stack map frame type.
 */
public final class ChopFrame extends StackMapFrame {

    private int offsetDelta;

    public ChopFrame(final int tag) {
        super(tag);
    }

    public int getOffsetDelta() {
        return this.offsetDelta;
    }

    @Override
    StackMapFrame readInternal(final DataInputStream in) throws IOException {
        this.offsetDelta = in.readUnsignedShort();
        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(this.tag);
        out.writeShort(this.offsetDelta);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
