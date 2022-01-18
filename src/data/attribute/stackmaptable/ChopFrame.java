package data.attribute.stackmaptable;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

public final class ChopFrame extends StackMapFrame {

    private short offsetDelta;

    public ChopFrame(byte tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {
        offsetDelta = in.readUnsignedShort();
        return this;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        out.write(tag);
        writeShort(out, offsetDelta);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
