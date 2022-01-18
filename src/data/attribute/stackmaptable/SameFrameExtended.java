package data.attribute.stackmaptable;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

public final class SameFrameExtended extends StackMapFrame {

    private short offsetDelta;

    public SameFrameExtended(byte tag) {
        super(tag);
    }

    @Override
    StackMapFrame readInternal(DataInputStream in) throws IOException {

        offsetDelta = readShort(in);

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
