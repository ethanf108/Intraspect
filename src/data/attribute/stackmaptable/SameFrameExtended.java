package data.attribute.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.*;

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
    public void write(OutputStream out) throws IOException {
        out.write(tag);
        writeShort(out, offsetDelta);
    }

    @Override
    public int getDataLength() {
        return 3;
    }
}
