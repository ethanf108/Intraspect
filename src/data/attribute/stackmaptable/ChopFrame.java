package data.attribute.stackmaptable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class ChopFrame extends StackMapFrame {

    private int offsetDelta;

    public ChopFrame(int tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {
        offsetDelta = in.readUnsignedShort();
        return this;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeByte(tag);
        out.writeShort(offsetDelta);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
