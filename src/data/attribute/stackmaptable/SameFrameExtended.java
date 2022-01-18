package data.attribute.stackmaptable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class SameFrameExtended extends StackMapFrame {

    private int offsetDelta;

    public SameFrameExtended(int tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {

        offsetDelta = in.readUnsignedShort();

        return this;
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeByte(tag);
        out.writeShort(offsetDelta);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
